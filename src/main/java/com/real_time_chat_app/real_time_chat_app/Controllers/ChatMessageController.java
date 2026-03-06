package com.real_time_chat_app.real_time_chat_app.Controllers;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/real-time-chat-app")
public class ChatMessageController {
    private final SimpMessageSendingOperations messagingTemplate;
    public ChatMessageController(SimpMessageSendingOperations messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void handleMessage(ChatMessage message, Principal principal){
        message.setSender(principal.getName());
        message.setTimestamp(Instant.now().toString());
        message.setMessageId(UUID.randomUUID().toString());
        messagingTemplate.convertAndSend("/topic/"+message.getRoomId(), message);
    }

}

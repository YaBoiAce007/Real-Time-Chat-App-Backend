package com.real_time_chat_app.real_time_chat_app.Controllers;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatMessage;
import com.real_time_chat_app.real_time_chat_app.Services.ChatMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/real-time-chat-app")
public class ChatMessageController {
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageService chatMessageService;

    public ChatMessageController(SimpMessageSendingOperations messagingTemplate, ChatMessageService chatMessageService){
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService=chatMessageService;
    }

    @MessageMapping("/group")
    public void sendMessageToGroup(ChatMessage message, Principal principal){
        ChatMessage savedMessage = chatMessageService.sendMessageToGroup(message, principal.getName());
        messagingTemplate.convertAndSend("/topic/"+savedMessage.getRoomId(), savedMessage);
    }

    @GetMapping("/rooms/{roomId}/messages")
    public List<ChatMessage> getMessages(@PathVariable String roomId, @RequestParam(required = false) Instant before){
        List<ChatMessage> messages = new ArrayList<>(chatMessageService.getMessages(roomId, before));
        Collections.reverse(messages);
        return messages;
    }
}

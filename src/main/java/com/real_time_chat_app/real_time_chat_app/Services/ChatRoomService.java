package com.real_time_chat_app.real_time_chat_app.Services;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatRoom;
import com.real_time_chat_app.real_time_chat_app.Repositories.ChatRoomRepo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ChatRoomService {
    private final ChatRoomRepo chatRoomRepo;
    private final SimpMessageSendingOperations messagingTemplate;
    public ChatRoomService(ChatRoomRepo chatRoomRepo, SimpMessageSendingOperations messagingTemplate){
        this.chatRoomRepo = chatRoomRepo;
        this.messagingTemplate = messagingTemplate;
    }
    public List<ChatRoom> getRooms(){
        return chatRoomRepo.findAll();
    }
    public ChatRoom createRoom(ChatRoom chatRoom, String username){
        if(chatRoomRepo.existsByName(chatRoom.getName())){
            return null;
        }
        chatRoom.setCreatedBy(username);
        chatRoom.setCreatedAt(Instant.now().toString());
        ChatRoom saved = chatRoomRepo.save(chatRoom);
        messagingTemplate.convertAndSend("/topic/rooms", saved);
        return saved;
    }
}

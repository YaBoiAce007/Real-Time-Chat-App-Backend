package com.real_time_chat_app.real_time_chat_app.Services;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatMessage;
import com.real_time_chat_app.real_time_chat_app.Repositories.ChatMessageRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ChatMessageService {
    private final ChatMessageRepo chatMessageRepo;
    public ChatMessageService(ChatMessageRepo chatMessageRepo){
        this.chatMessageRepo=chatMessageRepo;
    }
    public ChatMessage sendMessageToGroup(ChatMessage message, String sender){
        message.setSender(sender);
        message.setTimestamp(Instant.now());
        return chatMessageRepo.save(message);
    }
    public List<ChatMessage> getMessages(String roomId, Instant before){
        Pageable pageable = PageRequest.of(0, 20);

        if(before==null){
            return chatMessageRepo.findByRoomIdOrderByTimestampDesc(roomId, pageable).getContent();
        }

        return chatMessageRepo.findByRoomIdAndTimestampBeforeOrderByTimestampDesc(roomId, before, pageable);
    }
}

package com.real_time_chat_app.real_time_chat_app.Repositories;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage, String> {
    // Initial load — latest 20 for a room
    Page<ChatMessage> findByRoomIdOrderByTimestampDesc(String roomId, Pageable pageable);

    // Subsequent loads — 20 messages older than the cursor timestamp
    List<ChatMessage> findByRoomIdAndTimestampBeforeOrderByTimestampDesc(String roomId, Instant before, Pageable pageable);
}

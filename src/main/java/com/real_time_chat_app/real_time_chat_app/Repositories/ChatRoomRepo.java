package com.real_time_chat_app.real_time_chat_app.Repositories;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepo extends JpaRepository<ChatRoom, String> {
    public boolean existsByName(String name);
}

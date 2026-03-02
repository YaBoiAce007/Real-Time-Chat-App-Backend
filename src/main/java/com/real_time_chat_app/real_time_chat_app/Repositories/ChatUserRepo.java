package com.real_time_chat_app.real_time_chat_app.Repositories;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepo extends JpaRepository<ChatUser, Integer> {

    @Query(
            "SELECT u from ChatUser u WHERE u.username=:username"
    )
    ChatUser getUserByUsername(String username);
}

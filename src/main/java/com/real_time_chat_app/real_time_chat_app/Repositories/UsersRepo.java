package com.real_time_chat_app.real_time_chat_app.Repositories;

import com.real_time_chat_app.real_time_chat_app.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    @Query(
            "SELECT u from Users u WHERE u.username=:username"
    )
    Users getUserByUsername(String username);
}

package com.real_time_chat_app.real_time_chat_app.Security;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatUser;
import com.real_time_chat_app.real_time_chat_app.Repositories.ChatUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final ChatUserRepo chatUserRepo;

    public MyUserDetailsService(ChatUserRepo chatUserRepo) {
        this.chatUserRepo = chatUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ChatUser user = chatUserRepo.getUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}

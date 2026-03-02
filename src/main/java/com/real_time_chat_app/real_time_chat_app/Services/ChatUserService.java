package com.real_time_chat_app.real_time_chat_app.Services;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatUser;
import com.real_time_chat_app.real_time_chat_app.Repositories.ChatUserRepo;
import com.real_time_chat_app.real_time_chat_app.Security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChatUserService {

    private final ChatUserRepo chatUserRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtService jwtService;

    public ChatUserService(ChatUserRepo chatUserRepo, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService) {
        this.chatUserRepo = chatUserRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    public ChatUser addUser(ChatUser user){
        ChatUser existingUser = chatUserRepo.getUserByUsername(user.getUsername());
        if(existingUser!=null){
            return null;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return chatUserRepo.save(user);
    }

    public String authenticate(String username, String password){
        ChatUser user = chatUserRepo.getUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Incorrect Password");
        }
        return jwtService.generateToken(username);
    }
}

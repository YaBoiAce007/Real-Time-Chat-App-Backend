package com.real_time_chat_app.real_time_chat_app.Services;

import com.real_time_chat_app.real_time_chat_app.Entities.Users;
import com.real_time_chat_app.real_time_chat_app.Repositories.UsersRepo;
import com.real_time_chat_app.real_time_chat_app.Security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepo usersRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtService jwtService;

    public UsersService(UsersRepo usersRepo, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService) {
        this.usersRepo = usersRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    public Users addUser(Users user){
        Users existingUser = usersRepo.getUserByUsername(user.getUsername());
        if(existingUser!=null){
            return null;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return usersRepo.save(user);
    }

    public String authenticate(String username, String password){
        Users user = usersRepo.getUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Incorrect Password");
        }
        return jwtService.generateToken(username);
    }
}

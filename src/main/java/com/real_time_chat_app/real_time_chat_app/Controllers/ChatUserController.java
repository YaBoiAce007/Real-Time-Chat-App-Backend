package com.real_time_chat_app.real_time_chat_app.Controllers;

import com.real_time_chat_app.real_time_chat_app.CustomExceptions.UsernameAlreadyExistsException;
import com.real_time_chat_app.real_time_chat_app.Entities.ChatUser;
import com.real_time_chat_app.real_time_chat_app.Services.ChatUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/real-time-chat-app")
public class ChatUserController {

    private final ChatUserService chatUserService;

    public ChatUserController(ChatUserService chatUserService) {
        this.chatUserService = chatUserService;
    }

    @GetMapping("/Greet")
    public String greet(){
        return "Hello";
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody ChatUser user) {
        ChatUser newUser = chatUserService.addUser(user);
        if(newUser==null){
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration was successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody ChatUser user){
        String jwtToken = chatUserService.authenticate(user.getUsername(), user.getPassword());
        return ResponseEntity.ok().header("authorization", "Bearer "+jwtToken).body("Login was successful");
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(){
        return ResponseEntity.ok("Token is valid");
    }
}

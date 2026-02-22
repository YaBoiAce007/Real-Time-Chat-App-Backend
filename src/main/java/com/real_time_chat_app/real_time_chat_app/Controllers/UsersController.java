package com.real_time_chat_app.real_time_chat_app.Controllers;

import com.real_time_chat_app.real_time_chat_app.CustomExceptions.UsernameAlreadyExistsException;
import com.real_time_chat_app.real_time_chat_app.Entities.Users;
import com.real_time_chat_app.real_time_chat_app.Services.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/real-time-chat-app")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/Greet")
    public String greet(){
        return "Hello";
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
        Users newUser = usersService.addUser(user);
        if(newUser==null){
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration was successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody Users user){
        String jwtToken = usersService.authenticate(user.getUsername(), user.getPassword());
        return ResponseEntity.ok().header("authorization", "Bearer "+jwtToken).body("Login was successful");
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(){
        return ResponseEntity.ok("Token is valid");
    }
}

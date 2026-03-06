package com.real_time_chat_app.real_time_chat_app.Controllers;

import com.real_time_chat_app.real_time_chat_app.Entities.ChatRoom;
import com.real_time_chat_app.real_time_chat_app.Services.ChatRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/real-time-chat-app")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    public ChatRoomController(ChatRoomService chatRoomService){
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoom>> getRooms(){
        return ResponseEntity.ok(chatRoomService.getRooms());
    }

    @PostMapping("/rooms")
    public ResponseEntity<String> createRoom(@RequestBody ChatRoom chatRoom, Principal principal){
        ChatRoom saved = chatRoomService.createRoom(chatRoom, principal.getName());
        if(saved==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Room name already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Room "+saved.getName()+" was successfully created");
    }
}

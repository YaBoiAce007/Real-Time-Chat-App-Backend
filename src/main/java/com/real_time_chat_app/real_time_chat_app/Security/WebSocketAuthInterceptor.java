package com.real_time_chat_app.real_time_chat_app.Security;

import io.jsonwebtoken.ExpiredJwtException;
import org.jspecify.annotations.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor {
    private final JwtService jwtService;
    public WebSocketAuthInterceptor(JwtService jwtService){
        this.jwtService=jwtService;
    }
    @Override
    public @Nullable Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if(StompCommand.CONNECT.equals(accessor.getCommand())){
            String authHeader = accessor.getFirstNativeHeader("authorization");
            if(authHeader==null || !authHeader.startsWith("Bearer ")){
                return null;
            }
            String token = authHeader.substring(7);
            String username;
            try{
                username = jwtService.extractUsername(token);
            }
            catch (ExpiredJwtException e){
                return null;
            }
            accessor.setUser(new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()));
        }
        return message;
    }
}

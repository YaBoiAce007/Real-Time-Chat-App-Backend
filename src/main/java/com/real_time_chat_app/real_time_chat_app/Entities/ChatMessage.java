package com.real_time_chat_app.real_time_chat_app.Entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
        name="ChatMessages",
        indexes = @Index(columnList = "roomId, timestamp DESC")
)
public class ChatMessage {
    private String text;
    private String sender;       // username extracted from JWT
    private String roomId;       // which room this belongs to
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String messageId;    // UUID for React key prop + deduplication
    private Instant timestamp;    // ISO string, set server-side

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

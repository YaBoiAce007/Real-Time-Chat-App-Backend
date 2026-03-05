package com.real_time_chat_app.real_time_chat_app.Entities;

public class ChatMessage {
    private String text;
    private String sender;       // username extracted from JWT
    private String roomId;       // which room this belongs to
    private String messageId;    // UUID for React key prop + deduplication
    private String timestamp;    // ISO string, set server-side

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

package com.example.babur.chatapp.Models;

/**
 * Created by babur on 20-02-2018.
 */

public class Message
{
    String message;
    long createdAt;
    User sender;
    boolean sent;


    public Message(String message, boolean sent, long createdAt) {
        this.message = message;
        this.sent = sent;
        this.createdAt = createdAt;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCreatedAt() {return createdAt;}

    public void setCreatedAt(long createdAt)

    {
        this.createdAt = createdAt;
    }

    public User getSender() { return sender;  }

    public void setSender(User sender) { this.sender = sender; }

    public boolean isSent() {return sent;}

    public void setSent(boolean sent) {this.sent = sent;}
}
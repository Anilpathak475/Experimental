package com.bitnudge.ime.demo.modle;

import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.Date;

public class ChatMessage implements IMessage {
    private static int ID;

    static {
        ID = 0;
    }

    private Date createdAt;
    private String text;
    private String id;
    private ChatAuthor author;

    public ChatMessage(String text, ChatAuthor.AuthorType authorType, Date createdAt) { this(text, new ChatAuthor(authorType), createdAt); }
    public ChatMessage(String text, ChatAuthor.AuthorType authorType) { this(text, new ChatAuthor(authorType)); }
    public ChatMessage(String text, ChatAuthor author) { this(text, author, new Date()); }
    public ChatMessage(String text, ChatAuthor author, Date createdAt) {
        ID++;
        this.createdAt = createdAt;

        this.text = text;
        this.author = author;
        this.id = String.valueOf(ID);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public ChatAuthor getUser() {
        return this.author;
    }

    @Override
    public Date getCreatedAt() {
        return this.createdAt;
    }
}
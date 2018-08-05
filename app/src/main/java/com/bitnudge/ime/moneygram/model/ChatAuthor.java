package com.bitnudge.ime.moneygram.model;

import com.stfalcon.chatkit.commons.models.IUser;

public class ChatAuthor implements IUser {
    private AuthorType type;

    public ChatAuthor(AuthorType type) {
        this.type = type;
    }

    @Override
    public String getId() {
        if (type == AuthorType.USER) return "1";
        else return "0";
    }

    @Override
    public String getName() {
        if (type == AuthorType.USER) return "Adhityan";
        else return "Bot";
    }

    @Override
    public String getAvatar() {
        if (type == AuthorType.USER)
            return "https://secure.meetupstatic.com/photos/member/a/2/9/f/member_270641631.jpeg";
        else return "https://infosertecblog.files.wordpress.com/2016/08/bot-de-telegram.jpg";
    }

    public enum AuthorType {
        USER,
        BOT
    }
}
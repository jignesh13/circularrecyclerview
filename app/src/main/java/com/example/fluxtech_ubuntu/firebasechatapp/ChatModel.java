package com.example.fluxtech_ubuntu.firebasechatapp;

public class ChatModel {
    private String text;

    public String getText() {
        return text;
    }

    public boolean Issend() {
        return issend;
    }

    private boolean issend;
    public ChatModel(String text,boolean issend)
    {
        this.text=text;
        this.issend=issend;

    }

    @Override
    public String toString() {
        return text;
    }
}

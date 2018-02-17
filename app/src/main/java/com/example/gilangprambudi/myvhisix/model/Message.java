package com.example.gilangprambudi.myvhisix.model;

/**
 * Created by Gilang Prambudi on 16/02/2018.
 */

public class Message {
    private String senderName, messageText, senderEmail;


    public Message() {
    }

    public Message(String senderName, String messageText, String senderEmail) {

        this.senderName = senderName;
        this.messageText = messageText;
        this.senderEmail = senderEmail;
    }

    public String getSenderName() {

        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }





}

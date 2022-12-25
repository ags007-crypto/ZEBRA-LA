package com.firstapp.zebra;

public class MessageModel {

    public String getUnread_count() {
        return unread_count;
    }

    public void setUnread_count(String unread_count) {
        this.unread_count = unread_count;
    }

    public MessageModel(String unread_count) {
        this.unread_count = unread_count;
    }

    private String msgId;
    private String senderId; private String unread_count;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public MessageModel() {
    }

    public MessageModel(String msgId, String senderId, String message, String read,String unread_count) {
        this.msgId = msgId;
        this.senderId = senderId;
        this.message = message;
        this.read = read;
        this.unread_count=unread_count;

    }

    private String message;
    private String read;
}


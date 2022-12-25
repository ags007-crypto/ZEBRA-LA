package com.firstapp.zebra;

public class UserModel {
    private String userId,userName,userEmail,userPassword,unreadcount,set;

    public String getUnreadcount() {
        return unreadcount;
    }

    public void setUnreadcount(String unreadcount) {
        this.unreadcount = unreadcount;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public UserModel(String userId, String userName, String userEmail, String userPassword, String unreadcount, String set) {
        this.userId =userId;
        this.userName=userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.unreadcount=unreadcount;
        this.set=set;
    }

    public UserModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

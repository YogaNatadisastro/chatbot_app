package com.app.chatbot.Model;

import android.widget.ImageView;

public class UserDetails {

    private String userId, userName, userEmail, userPassword, role, gender;

    private ImageView jenisGender;

    public UserDetails(){

    }


    public UserDetails(String userId, String userName, String userEmail, String userPassword, String role, String gender){
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = role;
        this.gender = gender;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ImageView getJenisGender() {
        return jenisGender;
    }

    public void setJenisGender(ImageView jenisGender) {
        this.jenisGender = jenisGender;
    }
}

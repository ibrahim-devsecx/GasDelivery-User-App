package com.ibm.gasapp.modules;

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {
    private String imageUri;
    private String fullName;
    private String phone;
    private String gender;

    public User() {
    }

    public User(String imgUri, String fullName, String phone, String gender) {
        this.imageUri = imageUri;
        this.fullName = fullName;
        this.phone = phone;
        this.gender = gender;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}

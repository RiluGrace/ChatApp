package com.example.babur.chatapp.Models;

/**
 * Created by babur on 20-02-2018.
 */

public class User
{

        String nickname;
        String profileUrl;

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

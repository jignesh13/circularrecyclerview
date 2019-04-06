package com.example.fluxtech_ubuntu.firebasechatapp;

public class ImageModel {
    public String getName() {
        return name;
    }

    public String getProfilepic() {
        return profilepic;
    }

    private String name;

    public ImageModel(String name, String profilepic) {
        this.name = name;
        this.profilepic = profilepic;
    }

    private String profilepic;
}

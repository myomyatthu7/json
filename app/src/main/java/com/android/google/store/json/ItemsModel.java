package com.android.google.store.json;

public class ItemsModel {
    private String imageUrl;
    private String creator;
    private String  likes;

    public ItemsModel(String imageUrl, String creator, String likes) {
        this.imageUrl = imageUrl;
        this.creator = creator;
        this.likes = likes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreator() {
        return creator;
    }

    public String  getLikes() {
        return likes;
    }
}

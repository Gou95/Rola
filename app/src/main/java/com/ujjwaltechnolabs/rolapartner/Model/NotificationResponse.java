package com.ujjwaltechnolabs.rolapartner.Model;

public class NotificationResponse {

    private String title ,discription;

    public NotificationResponse(String title, String discription) {
        this.title = title;
        this.discription = discription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}

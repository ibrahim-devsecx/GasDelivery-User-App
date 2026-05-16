package com.ibm.gasapp.modules;

import com.google.gson.annotations.SerializedName;

public class RootModel {

    @SerializedName("to") //  "to" changed to token
    private String topic;

    @SerializedName("notification")
    private NotificationModel notification;

    @SerializedName("data")
    private Request data;

    public RootModel(String topic, NotificationModel notification, Request data) {
        this.topic = topic;
        this.notification = notification;
        this.data = data;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public NotificationModel getNotification() {
        return notification;
    }

    public void setNotification(NotificationModel notification) {
        this.notification = notification;
    }

    public Request getData() {
        return data;
    }

    public void setData(Request data) {
        this.data = data;
    }
}

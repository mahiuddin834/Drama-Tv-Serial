package com.itnation.dramaserial.ModelClass;

public class FeedModel {

    String name, videoId;

    public FeedModel() {
    }

    public FeedModel(String name, String videoId) {
        this.name = name;
        this.videoId = videoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}

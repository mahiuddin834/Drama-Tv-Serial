package com.itnation.dramaserial.ModelClass;

public class PopularModel {

    String name, videoId;

    public PopularModel() {
    }

    public PopularModel(String name, String videoId) {
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

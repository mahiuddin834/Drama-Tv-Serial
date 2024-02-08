package com.itnation.dramaserial.ModelClass;

public class TodayExclusiveModel {

    String name, videoId;

    public TodayExclusiveModel() {
    }

    public TodayExclusiveModel(String name, String videoId) {
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

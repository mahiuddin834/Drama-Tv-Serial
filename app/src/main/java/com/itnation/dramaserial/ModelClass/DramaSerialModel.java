package com.itnation.dramaserial.ModelClass;

public class DramaSerialModel {

    String nameEp, videoId;

    public DramaSerialModel() {
    }

    public DramaSerialModel(String nameEp, String videoId) {
        this.nameEp = nameEp;
        this.videoId = videoId;
    }

    public String getNameEp() {
        return nameEp;
    }

    public void setNameEp(String nameEp) {
        this.nameEp = nameEp;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}

package com.itnation.dramaserial.ModelClass;

public class HindiDubbingModel {

    String serialName, imgUrl;

    public HindiDubbingModel() {
    }

    public HindiDubbingModel(String serialName, String imgUrl) {
        this.serialName = serialName;
        this.imgUrl = imgUrl;
    }

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

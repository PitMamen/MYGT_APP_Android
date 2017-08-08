package com.Szmygt.app.vr.api.bean;

import com.Szmygt.app.vr.api.VideoVo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Richie on 2017/7/5.
 */

public class MainIndexInfo implements Serializable {

    private  String imagePrefix;
    private  int pages;
    private List<VideoVo> videos;

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    public int getPahes() {
        return pages;
    }

    public void setPahes(int pahes) {
        this.pages = pahes;
    }

    public List<VideoVo> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoVo> videos) {
        this.videos = videos;
    }
}

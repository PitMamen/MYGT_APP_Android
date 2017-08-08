package com.Szmygt.app.vr.api.bean;

import com.Szmygt.app.vr.api.VideoVo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Richie on 2017/7/18.
 */

public class IndexInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String imagePrefix;
    private  List<VideoVo> choiceVideo;

    public List<VideoVo> getChoiceVideoList() {
        return choiceVideo;
    }

    public void setChoiceVideoList(List<VideoVo> choiceVideoList) {
        this.choiceVideo = choiceVideoList;
    }

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }
}

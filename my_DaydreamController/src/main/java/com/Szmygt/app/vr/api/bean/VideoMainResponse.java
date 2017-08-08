package com.Szmygt.app.vr.api.bean;

import java.io.Serializable;

/**
 * Created by Richie on 2017/7/5.
 */

public class VideoMainResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status;
    private  MainIndexInfo response;

    public MainIndexInfo getResponse() {
        return response;
    }

    public void setResponse(MainIndexInfo response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

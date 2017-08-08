package com.Szmygt.app.vr.api.bean;

import java.io.Serializable;

/**
 * Created by Richie on 2017/8/7.
 */

public class GameMainIndexResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status;
    private  GameIndexInfo response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GameIndexInfo getResponse() {
        return response;
    }

    public void setResponse(GameIndexInfo response) {
        this.response = response;
    }
}

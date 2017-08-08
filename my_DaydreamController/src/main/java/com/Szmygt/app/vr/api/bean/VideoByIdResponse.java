package com.Szmygt.app.vr.api.bean;

import java.io.Serializable;

/**
 * Created by Richie on 2017/7/18.
 */

public class VideoByIdResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private  StringUrl response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StringUrl getResponse() {
        return response;
    }

    public void setResponse(StringUrl response) {
        this.response = response;
    }

    public static  class  StringUrl{
        private static final long serialVersionUID = 1L;
         private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            url = url;
        }
    }


}

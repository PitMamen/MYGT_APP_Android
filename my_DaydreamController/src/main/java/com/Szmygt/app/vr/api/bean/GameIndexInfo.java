package com.Szmygt.app.vr.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Richie on 2017/8/7.
 */

public class GameIndexInfo  implements Serializable{
    private static final long serialVersionUID = 1L;
    private  String imagePrefix ;
    private List<GameVo> games;

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    public List<GameVo> getGames() {
        return games;
    }

    public void setGames(List<GameVo> games) {
        this.games = games;
    }
}

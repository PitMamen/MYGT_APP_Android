package com.Szmygt.app.vr.utils;

import android.content.ContextWrapper;

import com.unity3d.player.UnityPlayer;

/**
 * Created by Richie on 2017/7/6.
 */

public class UnityPlayActivity extends UnityPlayer {
    public UnityPlayActivity(ContextWrapper contextWrapper) {
        super(contextWrapper);
    }


    //重写该方法可去除 界面左右两边的图标
    @Override
    protected boolean setGoogleVREnabled(boolean b) {
        return false;
    }
}

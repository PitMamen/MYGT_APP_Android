package com.Szmygt.app.vr.provider;

import android.content.Context;
import android.util.Log;

import com.Szmygt.app.vr.api.VRRetrofitService;
import com.Szmygt.app.vr.api.VideoVo;
import com.Szmygt.app.vr.api.bean.GameIndexInfo;
import com.Szmygt.app.vr.api.bean.GameMainIndexResponse;
import com.Szmygt.app.vr.api.bean.GameVo;
import com.Szmygt.app.vr.utils.OKHttpUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Richie on 2017/8/7.
 */

public class GameListProvider {
    private final String TAG = getClass().getSimpleName();
    private Context mContext;
    private List<GameVo> mGameVoList;

    public GameListProvider(Context c) {
        this.mContext = c;
        getGameIndex();
    }

    public int getListSize() {
        return mGameVoList.size();
    }

    public GameVo get(int index) {

        return mGameVoList.get(index);
    }

    //获取最大页
    public int getMaxPageIndex(int pageImageCount) {
        int imageCount = mGameVoList.size();
        int maxPage = imageCount / pageImageCount + ((imageCount % pageImageCount == 0) ? 0 : 1) - 1;

        return maxPage;
    }


    public GameVo[] get(int pageIndex, int pageImageCount) {
        GameVo[] imageInfos;
        synchronized (mGameVoList) {
            int index;
            int count;
            int imageCount = mGameVoList.size();

            index = pageIndex * pageImageCount;
            if (index >= imageCount) {
                return new GameVo[0];
            }
            if (pageIndex == getMaxPageIndex(pageImageCount)) {
                count = imageCount % pageImageCount;
            } else {
                count = pageImageCount;
            }
            imageInfos = new GameVo[count];
            for (int i = 0; i < count; i++) {
                imageInfos[i] = mGameVoList.get(index + i);
            }
        }
        return imageInfos;

    }


    public String gameicon;
    public String gameid;
    public String gamename;
    public String gamepublicity;
    public String gamedescinfo;
    public int gameDomnCount;


    public String[] gameIcons;
    public String[] gameIds;
    public String[] gameNames;
    public String[] gamePublicitys;
    public String[] gameDescinfos;
    public int[] gameDomnCounts;


    public void getGameIndex() {
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getGameIndex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GameMainIndexResponse>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        //开始请求网络
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                        //数据获取完成，可以取消加载框
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(GameMainIndexResponse gameMainIndexResponse) {
                        String status = gameMainIndexResponse.getStatus();
                        Log.e("VideoListProvider==", "status4: " + status);
                        GameIndexInfo info = gameMainIndexResponse.getResponse();
                        String StringPrifix = info.getImagePrefix();
                        Log.e("VideoListProvider==", "StringPrifix4: " + StringPrifix);

                        mGameVoList = info.getGames();
                        Log.e("VideoListProvider==", "gameList.size== " + mGameVoList.size());

                        gameIcons = new String[mGameVoList.size()];
                        gameIds = new String[mGameVoList.size()];
                        gameNames = new String[mGameVoList.size()];
                        gamePublicitys = new String[mGameVoList.size()];
                        gameDescinfos = new String[mGameVoList.size()];
                        gameDomnCounts = new int[mGameVoList.size()];

                        GameVo mVo = new GameVo(mContext);


                        for (int i = 0; i < mGameVoList.size(); i++) {

                            GameVo vo = mGameVoList.get(i);

                            gameid = vo.getId();  //id
                            gameIds[i] = gameid;


                            gameicon = StringPrifix+vo.getIcon(); //图标
                            Log.e("gameicon", "gameicon:== "+gameicon );
                            gameIcons[i] = gameicon;


                            gamename = vo.getGamename(); //名称
                            gameNames[i] = gamename;


                            gamedescinfo = vo.getDescinfo();  //描述
                            gameDescinfos[i] = gamedescinfo;


                            gamepublicity = StringPrifix+vo.getPublicity(); //宣传图
                            Log.e("gameicon", "gamepublicity:== "+gamepublicity );
                            gamePublicitys[i] = gamepublicity;



                            gameDomnCount = vo.getDownloadnumber();    //下载量
                            Log.e("gameicon", "gameDomnCount:== "+gameDomnCount );
                            gameDomnCounts[i] = gameDomnCount;



                        }


                    }
                });
    }


    public String[] getGameID() {
        if (gameIds != null) {
            return gameIds;
        }

        return null;
    }


    public String[] getGameIcon() {
        if (gameIcons != null) {
            return gameIcons;
        }
        return null;
    }


    public String[] getGameName() {
        if (gameNames != null) {
            return gameNames;
        }
        return null;
    }


    public String[] getGamePublicity() {
        if (gamePublicitys != null) {
            return gamePublicitys;
        }

        return null;
    }


    public String[] getGameDescinfo() {
        if (gameDescinfos != null) {
            return gameDescinfos;
        }

        return null;
    }


    public int[] getGameDownCount(){
        if (gameDomnCounts!=null){
            return gameDomnCounts;
        }

        return null;
    }


}

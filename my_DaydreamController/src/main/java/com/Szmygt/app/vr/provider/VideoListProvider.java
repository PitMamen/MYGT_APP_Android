package com.Szmygt.app.vr.provider;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.Szmygt.app.vr.api.VRRetrofitService;
import com.Szmygt.app.vr.api.bean.GameIndexInfo;
import com.Szmygt.app.vr.api.bean.GameMainIndexResponse;
import com.Szmygt.app.vr.api.bean.GameVo;
import com.Szmygt.app.vr.api.bean.IndexInfo;
import com.Szmygt.app.vr.api.bean.IndexResponse;
import com.Szmygt.app.vr.api.VideoVo;
import com.Szmygt.app.vr.api.bean.MainIndexInfo;
import com.Szmygt.app.vr.api.bean.VideoByIdResponse;
import com.Szmygt.app.vr.api.bean.VideoMainResponse;
import com.Szmygt.app.vr.utils.OKHttpUtil;
import com.bumptech.glide.Glide;
import com.unity3d.player.UnityPlayer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Richie on 2017/7/18.
 */

public class VideoListProvider {

    private final String TAG = getClass().getSimpleName();


    private Context context;
    private List<VideoVo> mVideoVoList;
    public byte[] mBytesIcon;
    public boolean isProviderReady = false;
    CopyOnWriteArrayList<VideoVo> imageList;

    public String mThumbnails;
    public String mVideoName;
    public String mDescription;
    public String VideoTime;
    public String VideoID;

    public String[] mStringVideoIDs;
    public String[] mStringTimes;
    public String[] mStrings;
    public String[] mDescriptions;
    public String[] mVideoNames;
    private String mUrl;

    public VideoListProvider(Context c) {
        this.context = c;
        testGetHomeIndex();
//        testgetVideoById();
//        testMainVideo();
        imageList = new CopyOnWriteArrayList<>();

    }

    public int getListSize() {
        return imageList.size();
    }

    public VideoVo get(int index) {

        return imageList.get(index);
    }

    //获取最大页
    public int getMaxPageIndex(int pageImageCount) {
        int imageCount = imageList.size();
        int maxPage = imageCount / pageImageCount + ((imageCount % pageImageCount == 0) ? 0 : 1) - 1;

        return maxPage;
    }


    public VideoVo[] get(int pageIndex, int pageImageCount) {
        VideoVo[] imageInfos;
        synchronized (imageList) {
            int index;
            int count;
            int imageCount = imageList.size();

            index = pageIndex * pageImageCount;
            if (index >= imageCount) {
                return new VideoVo[0];
            }
            if (pageIndex == getMaxPageIndex(pageImageCount)) {
                count = imageCount % pageImageCount;
            } else {
                count = pageImageCount;
            }
            imageInfos = new VideoVo[count];
            for (int i = 0; i < count; i++) {
                imageInfos[i] = imageList.get(index + i);
            }
        }
        return imageInfos;

    }


    public synchronized void testGetHomeIndex() {
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getHomeIndex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IndexResponse>() {

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
                    public void onNext(IndexResponse indexResponse) {

                        Log.d(TAG, "onNext: " + indexResponse);
                        //取数据展示，主线程
                        String decoString = indexResponse.getResponse().toString();
                        Log.e("PXK", "decoString:   " + decoString);
                        String stauts = indexResponse.getStatus();
                        Log.e("PXK", "stauts:   " + stauts);
                        IndexInfo info = indexResponse.getResponse();

                        String imagePrefix = info.getImagePrefix();
                        Log.e("PXK", "imagePrefix:   " + imagePrefix);

                        mVideoVoList = info.getChoiceVideoList();
                        Log.e("PXK", "list:   " + mVideoVoList.size());

                        VideoVo mVo = new VideoVo(context);

                        mStrings = new String[mVideoVoList.size()];
                        mDescriptions = new String[mVideoVoList.size()];
                        mVideoNames = new String[mVideoVoList.size()];
                        mStringVideoIDs = new String[mVideoVoList.size()];
                        mStringTimes = new String[mVideoVoList.size()];

                        for (int i = 0; i < mVideoVoList.size(); i++) {
                            VideoVo video = mVideoVoList.get(i);

                            VideoID = video.getId();
                            Log.d("mDescription", "VideoID: " + VideoID);
                            mStringVideoIDs[i] = VideoID;


                            VideoTime = video.getDuration();  //时间
                            Log.d("mDescription", "VideoTime: " + VideoTime);
                            mStringTimes[i] = VideoTime;

                            mDescription = video.getDescription();   //描述
                            Log.d("mDescription", "mDescription: " + mDescription);
                            mDescriptions[i] = mDescription;

                            mVideoName = video.getVideoname();    //名称
                            mVideoNames[i] = mVideoName;
                            Log.d("mDescription", "mVideoName: " + mVideoName);

                            mThumbnails = imagePrefix + video.getThumbnails();  //缩略图
                            Log.e("HYZ", "mThumbnails: " + mThumbnails);
                            mStrings[i] = mThumbnails;            //将每个缩略图用String数组保存

                            mVo.setThumbnails(mThumbnails);
                            Log.d("DMJ", "onNext: " + mVo.getThumbnails());
                            imageList.add(mVo);
                        }

                    }


                });
    }

    //获取视频资源的播放路径
    public void testgetVideoById(String VideoID) {
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getVideoById(VideoID)   //10010020170427142230865001
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoByIdResponse>() {

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
                    public void onNext(VideoByIdResponse videoByIdResponse) {

                        String status = videoByIdResponse.getStatus();
                        Log.e("LYF", "status2: " + status);
                        VideoByIdResponse.StringUrl StringUrl = videoByIdResponse.getResponse();
                        Log.e("LYF", "StringUrl: " + StringUrl);
                        mUrl = StringUrl.getUrl();
                        Log.e("ZZX", "VideoUrl: " + mUrl);
                    }
                });
    }



    public String[] getStringArray() {
        if (mStrings != null) {
            return mStrings;
        }
        return null;
    }

    public String[] getVideoName() {
        if (mVideoNames != null) {
            return mVideoNames;
        }

        return null;
    }

    public String[] getDescription() {
        if (mDescriptions != null) {
            return mDescriptions;
        }
        return null;
    }


    public String[] getVideoID() {
        if (mStringVideoIDs != null) {
            return mStringVideoIDs;
        }
        return null;
    }


    public String[] getVideoTime() {
        if (mStringTimes != null) {
            return mStringTimes;
        }
        return null;
    }


    public String getVideoUrl() {
        if (mUrl != null) {
            return mUrl;
        }
        return null;
    }




}

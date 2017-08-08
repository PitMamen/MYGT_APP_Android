package com.Szmygt.app.vr.provider;

import android.content.Context;
import android.util.Log;

import com.Szmygt.app.vr.api.VRRetrofitService;
import com.Szmygt.app.vr.api.VideoVo;
import com.Szmygt.app.vr.api.bean.IndexInfo;
import com.Szmygt.app.vr.api.bean.IndexResponse;
import com.Szmygt.app.vr.api.bean.MainIndexInfo;
import com.Szmygt.app.vr.api.bean.VideoByIdResponse;
import com.Szmygt.app.vr.api.bean.VideoMainResponse;
import com.Szmygt.app.vr.utils.OKHttpUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Richie on 2017/8/5.
 */

public class VideoMainListProvider {

    private final String TAG = getClass().getSimpleName();


    private Context context;
    //    private List<VideoVo> mVideoVoList;
//    CopyOnWriteArrayList<VideoVo> imageList;
    public List<VideoVo> mMianVideoList;
    public String mThumbnails;  //缩略图
    public String mDescription;   //描述
    public String mVideoName;    //名称
    public String mVideoTime;    //时长
    public String mVideoID;       //ID


    public String[] mStringVideoIDs;
    public String[] mStringTimes;
    public String[] mStrings;
    public String[] mDescriptions;
    public String[] mVideoNames;


    public VideoMainListProvider(Context c) {
        this.context = c;
//        testMainVideo();

    }

    public int getListSize() {
        return mMianVideoList.size();
    }

    public VideoVo get(int index) {

        return mMianVideoList.get(index);
    }

    //获取最大页
    public int getMaxPageIndex(int pageImageCount) {
        int imageCount = mMianVideoList.size();
        int maxPage = imageCount / pageImageCount + ((imageCount % pageImageCount == 0) ? 0 : 1) - 1;

        return maxPage;
    }


    public VideoVo[] get(int pageIndex, int pageImageCount) {
        VideoVo[] imageInfos;
        synchronized (mMianVideoList) {
            int index;
            int count;
            int imageCount = mMianVideoList.size();

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
                imageInfos[i] = mMianVideoList.get(index + i);
            }
        }
        return imageInfos;

    }


    public void testMainVideo(String Type) {
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getMainIndex(Type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoMainResponse>() {

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
                    public void onNext(VideoMainResponse videoMainResponse) {

                        String status = videoMainResponse.getStatus();
                        Log.e("LYF", "status3: " + status);

                        MainIndexInfo info = videoMainResponse.getResponse();
                        int pages = info.getPahes();
                        Log.e("LYF", "pages3: " + pages);
                        String imagePrefix = info.getImagePrefix();
                        Log.e("LYF", "imagePrefix3: " + imagePrefix);
                        mMianVideoList = info.getVideos();
                        Log.e("LYF", "videoVoList3: " + mMianVideoList.size());
//                        VideoVo   mVo = new VideoVo(context);


                        mStrings = new String[mMianVideoList.size()];
                        mDescriptions = new String[mMianVideoList.size()];
                        mVideoNames = new String[mMianVideoList.size()];
                        mStringVideoIDs = new String[mMianVideoList.size()];
                        mStringTimes = new String[mMianVideoList.size()];

                        VideoVo mVo = new VideoVo(context);

                        for (int i = 0; i < mMianVideoList.size(); i++) {
                            VideoVo vo = mMianVideoList.get(i);
                            mThumbnails = imagePrefix + vo.getThumbnails();
                            mStrings[i] = mThumbnails;


                            mDescription = vo.getDescription();
                            mDescriptions[i] = mDescription;

                            mVideoName = vo.getVideoname();
                            mVideoNames[i] = mVideoName;

                            mVideoID = vo.getId();
                            mStringVideoIDs[i] = mVideoID;

                            mVideoTime = vo.getDuration();
                            mStringTimes[i] = mVideoTime;
                            mVo.setThumbnails(mThumbnails);

                        }


                    }
                });
    }


    public String[] getStringArray() {
        if (mStrings != null) {
            return mStrings;
        }
        return null;
    }

    public  String[] getVideoName(){
        if (mVideoNames!=null){
            return  mVideoNames;
        }

        return null;
    }

    public  String[] getDescription(){
        if (mDescriptions!=null){
            return mDescriptions;
        }
        return  null;
    }


    public  String[] getVideoID(){
        if (mStringVideoIDs!=null){
            return mStringVideoIDs;
        }
        return  null;
    }


    public  String[] getVideoTime()
    {
        if (mStringTimes!=null){
            return mStringTimes;
        }
        return null;
    }















//    public synchronized void testGetHomeIndex() {
//        VRRetrofitService service = OKHttpUtil.getBaseService();
//        service.getHomeIndex()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<IndexResponse>() {
//
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        //开始请求网络
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "onCompleted: ");
//                        //数据获取完成，可以取消加载框
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: " + e.toString());
//                    }
//
//                    @Override
//                    public void onNext(IndexResponse indexResponse) {
//
//                        Log.d(TAG, "onNext: " + indexResponse);
//                        //取数据展示，主线程
//                        String decoString = indexResponse.getResponse().toString();
//                        Log.e("PXK", "decoString:   " + decoString);
//                        String stauts = indexResponse.getStatus();
//                        Log.e("PXK", "stauts:   " + stauts);
//                        IndexInfo info = indexResponse.getResponse();
//
//                        String imagePrefix = info.getImagePrefix();
//                        Log.e("PXK", "imagePrefix:   " + imagePrefix);
//
//                        mVideoVoList = info.getChoiceVideoList();
//                        Log.e("PXK", "list:   " + mVideoVoList.size());
//
//                        VideoVo   mVo = new VideoVo(context);
//
//                        mStrings = new String[mVideoVoList.size()];
//                        mDescriptions = new String[mVideoVoList.size()];
//                        mVideoNames = new String[mVideoVoList.size()];
//                        mStringVideoIDs = new String[mVideoVoList.size()];
//                        mStringTimes = new String[mVideoVoList.size()];
//
//                        for (int i = 0; i < mVideoVoList.size(); i++) {
//                            VideoVo video = mVideoVoList.get(i);
//
//                            VideoID = video.getId();
//                            Log.d("mDescription", "VideoID: "+VideoID);
//                            mStringVideoIDs[i] = VideoID;
//
//
//                            VideoTime = video.getDuration();  //时间
//                            Log.d("mDescription", "VideoTime: "+VideoTime);
//                            mStringTimes[i] = VideoTime;
//
//                            mDescription = video.getDescription();   //描述
//                            Log.d("mDescription", "mDescription: "+mDescription);
//                            mDescriptions[i] = mDescription;
//
//                            mVideoName = video.getVideoname();    //名称
//                            mVideoNames[i] = mVideoName;
//                            Log.d("mDescription", "mVideoName: "+mVideoName);
//
//                            mThumbnails = imagePrefix + video.getThumbnails();  //缩略图
//                            Log.e("HYZ", "mThumbnails: " + mThumbnails);
//                            mStrings[i] = mThumbnails;            //将每个缩略图用String数组保存
//
//                            mVo.setThumbnails(mThumbnails);
//                            Log.d("DMJ", "onNext: " + mVo.getThumbnails());
//                            imageList.add(mVo);
//                        }
//
//                    }
//
//
//
//                });
//    }
//
//    //获取视频资源的播放路径
//    public void testgetVideoById() {
//        VRRetrofitService service = OKHttpUtil.getBaseService();
//        service.getVideoById("10010020170427142230865001")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<VideoByIdResponse>() {
//
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        //开始请求网络
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "onCompleted: ");
//                        //数据获取完成，可以取消加载框
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: " + e.toString());
//                    }
//
//                    @Override
//                    public void onNext(VideoByIdResponse videoByIdResponse) {
//
//                        String status = videoByIdResponse.getStatus();
//                        Log.e("LYF", "status2: " + status);
//                        VideoByIdResponse.StringUrl StringUrl = videoByIdResponse.getResponse();
//                        Log.e("LYF", "StringUrl: " + StringUrl);
//                        String Url = StringUrl.getUrl();
//                        Log.e("LYF", "Url: " + Url);
//                    }
//                });
//    }


//    public String[] getStringArray() {
//        if (mStrings != null) {
//            return mStrings;
//        }
//        return null;
//    }
//
//    public  String[] getVideoName(){
//        if (mVideoNames!=null){
//            return  mVideoNames;
//        }
//
//        return null;
//    }
//
//    public  String[] getDescription(){
//        if (mDescriptions!=null){
//            return mDescriptions;
//        }
//        return  null;
//    }
//
//
//    public  String[] getVideoID(){
//        if (mStringVideoIDs!=null){
//            return mStringVideoIDs;
//        }
//        return  null;
//    }
//
//
//    public  String[] getVideoTime()
//    {
//        if (mStringTimes!=null){
//            return mStringTimes;
//        }
//        return null;
//    }


}

package com.Szmygt.app.vr.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.unity3d.player.UnityPlayer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author zxy
 * @date 2017年4月20日 上午9:41:15
 */
public class VideoVo implements Parcelable {

    private static final long serialVersionUID = 1L;
    public final String TAG = getClass().getSimpleName();
    private Context mContext;

    public VideoVo(Parcel in) {
        readFromParcel(in);
    }


    public VideoVo(Context c) {
        this.mContext = c;

    }

    public VideoVo()
    {

    }

    @Override
    public String toString() {
        return "VideoVo{" +
                "id='" + id + '\'' +
                ", videoname='" + videoname + '\'' +
                ", thumbnails='" + thumbnails + '\'' +
                ", duration='" + duration + '\'' +
                ", year='" + year + '\'' +
                ", laudnumber=" + laudnumber +
                ", downloadnumber=" + downloadnumber +
                ", playnumber=" + playnumber +
                ", collectnumber=" + collectnumber +
                ", labels='" + labels + '\'' +
                ", classification='" + classification + '\'' +
                ", description='" + description + '\'' +
                ", detaileddescription='" + detaileddescription + '\'' +
                '}';
    }

    public Bitmap thumbnail;

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    private String id;

    /**
     * 视频名称
     */
    private String videoname;

    /**
     * 缩略图
     */
    private String thumbnails;

    /**
     * 视频时长
     */
    private String duration;

    /**
     * 年份
     */
    private String year;

    /**
     * 获赞次数
     */
    private Integer laudnumber;

    /**
     * 下载次数
     */
    private Integer downloadnumber;

    /**
     * 播放次数
     */
    private Integer playnumber;

    /**
     * 收藏次数
     */
    private Integer collectnumber;

    /**
     * 标签:4K、3D、全景等
     */
    private String labels;

    /**
     * 所属分类:性感美女、极致美景、劲爆热舞、游戏视频、开心娱乐、科幻惊悚、文艺知音、高清MV等
     */
    private String classification;

    /**
     * 概要描述
     */
    private String description;

    /**
     * 详细描述
     */
    private String detaileddescription;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getLaudnumber() {
        return laudnumber;
    }

    public void setLaudnumber(Integer laudnumber) {
        this.laudnumber = laudnumber;
    }

    public Integer getDownloadnumber() {
        return downloadnumber;
    }

    public void setDownloadnumber(Integer downloadnumber) {
        this.downloadnumber = downloadnumber;
    }

    public Integer getPlaynumber() {
        return playnumber;
    }

    public void setPlaynumber(Integer playnumber) {
        this.playnumber = playnumber;
    }

    public Integer getCollectnumber() {
        return collectnumber;
    }

    public void setCollectnumber(Integer collectnumber) {
        this.collectnumber = collectnumber;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetaileddescription() {
        return detaileddescription;
    }

    public void setDetaileddescription(String detaileddescription) {
        this.detaileddescription = detaileddescription;
    }

//    private void readFromParcel(Parcel in) {
//        id = in.readString();
//        videoname = in.readString();
//        thumbnails = in.readString();
//        duration = in.readString();
//        year = in.readString();
//        laudnumber = in.readInt();
//        downloadnumber = in.readInt();
//        playnumber = in.readInt();
//        collectnumber = in.readInt();
//        labels = in.readString();
//        classification = in.readString();
//        description = in.readString();
//        detaileddescription = in.readString();
//    }


    private void readFromParcel(Parcel in) {
        id = in.readString();
        videoname = in.readString();
        thumbnails = in.readString();
        duration = in.readString();
    }


    public boolean isIconReady = false;
    public byte[] iconBytes;




    public void loadIcon() {
        try {

            ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, outputstream);
            iconBytes = outputstream.toByteArray();

            isIconReady = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public byte[] loadIcon1(String path)
    {

        try {
            thumbnail = Glide.with(mContext).load(path).asBitmap().centerCrop().into(140, 140).get();
            ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, outputstream);
            return outputstream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
           return null;
    }



    public void BitmapBytes(String path) throws IOException {

        InputStream stream = new FileInputStream(new File(path));

        Log.d(TAG, "path--: " + path);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, bao);


        iconBytes= bao.toByteArray();
        Log.d("DLJ", "BitmapBytes===: " + iconBytes.length);
        isIconReady = true;
        bitmap.recycle();
    }


    public static final Parcelable.Creator<VideoVo> CREATOR = new Parcelable.Creator<VideoVo>() {

        @Override
        public VideoVo createFromParcel(Parcel source) {
            return new VideoVo(source);
        }

        @Override
        public VideoVo[] newArray(int size) {

            return new VideoVo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(thumbnails);
        dest.writeString(id);
        dest.writeString(detaileddescription);

    }
}

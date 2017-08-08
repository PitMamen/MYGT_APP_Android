package com.Szmygt.app.vr.api.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.Szmygt.app.vr.api.VideoVo;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import static android.os.UserHandle.readFromParcel;

/**
 * @author zxy
 * @date 2017年4月20日 上午9:44:20
 * 
 */
public class GameVo implements Parcelable {
	private static final long serialVersionUID = 1L;
	private String id;  //主键
	private String gamename;  //游戏名称
	private String icon;  //游戏icon
	private String publicity;  //宣传图
	private String type;       //游戏类型
	private String groups;      // 游戏分组
	private String lables;     //游戏标签
	private String descinfo;   //描述
	private String version;    //版本号
	private String apksize;      //文件大小
	private Integer downloadnumber;  //下载次数
	private Bitmap thumbnail;



	public final String TAG = getClass().getSimpleName();
	private Context mContext;

	public GameVo(Parcel in) {
		readFromParcel(in);
	}


	public GameVo(Context c) {
		this.mContext = c;

	}

	public GameVo()
	{

	}


	private void readFromParcel(Parcel in) {
		id = in.readString();
		gamename = in.readString();
		publicity = in.readString();
		descinfo = in.readString();
		downloadnumber = in.readInt();
		icon = in.readString();
	}


	@Override
	public String toString() {
		return "GameVo{" +
				"id='" + id + '\'' +
				", gamename='" + gamename + '\'' +
				", icon='" + icon + '\'' +
				", publicity='" + publicity + '\'' +
				", type='" + type + '\'' +
				", groups='" + groups + '\'' +
				", lables='" + lables + '\'' +
				", descinfo='" + descinfo + '\'' +
				", version='" + version + '\'' +
				", apksize='" + apksize + '\'' +
				", downloadnumber=" + downloadnumber +
				", thumbnail=" + thumbnail +
				", TAG='" + TAG + '\'' +
				", mContext=" + mContext +
				'}';
	}

	public Bitmap getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Bitmap thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPublicity() {
		return publicity;
	}
	public void setPublicity(String publicity) {
		this.publicity = publicity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getLables() {
		return lables;
	}
	public void setLables(String lables) {
		this.lables = lables;
	}
	public String getDescinfo() {
		return descinfo;
	}
	public void setDescinfo(String descinfo) {
		this.descinfo = descinfo;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getApksize() {
		return apksize;
	}
	public void setApksize(String apksize) {
		this.apksize = apksize;
	}
	public Integer getDownloadnumber() {
		return downloadnumber;
	}
	public void setDownloadnumber(Integer downloadnumber) {
		this.downloadnumber = downloadnumber;
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




















	public static final Parcelable.Creator<GameVo> CREATOR = new Parcelable.Creator<GameVo>() {

		@Override
		public GameVo createFromParcel(Parcel source) {
			return new GameVo(source);
		}

		@Override
		public GameVo[] newArray(int size) {

			return new GameVo[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(publicity);
		dest.writeString(icon);
		dest.writeString(id);
		dest.writeString(gamename);

	}




}

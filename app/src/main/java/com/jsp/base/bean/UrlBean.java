package com.jsp.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2016/11/21.
 * 打开网页时显示的WebViewActivity页面所需数据
 * 除url外其他可不传
 */
public class UrlBean implements Parcelable {



    public String url;

    public String title;

    public String lastTitle = "返回";

    public String avater;

    public boolean isFreshVisible = true;

    public boolean isShareVisible = true;

    public UrlBean(String url) {
        this.url = url;
    }

    public UrlBean(String url, String title, String avater) {
        this.url = url;
        this.title = title;
        this.avater = avater;
    }

    public UrlBean(String url, String title, String lastTitle, String avater, boolean isFreshVisible, boolean isShareVisible) {
        this.url = url;
        this.title = title;
        this.lastTitle = lastTitle;
        this.avater = avater;
        this.isFreshVisible = isFreshVisible;
        this.isShareVisible = isShareVisible;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.lastTitle);
        dest.writeString(this.avater);
        dest.writeByte(this.isFreshVisible ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isShareVisible ? (byte) 1 : (byte) 0);
    }

    protected UrlBean(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
        this.lastTitle = in.readString();
        this.avater = in.readString();
        this.isFreshVisible = in.readByte() != 0;
        this.isShareVisible = in.readByte() != 0;
    }

    public static final Parcelable.Creator<UrlBean> CREATOR = new Parcelable.Creator<UrlBean>() {
        @Override
        public UrlBean createFromParcel(Parcel source) {
            return new UrlBean(source);
        }

        @Override
        public UrlBean[] newArray(int size) {
            return new UrlBean[size];
        }
    };
}

package com.example.fundamental1.Api;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserData {
    private String name;
    private String url;

    public UserData(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserData() {
    }


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.url);
    }

    private UserData(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }
    public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel source) {
            return new UserData(source);
        }
        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
}

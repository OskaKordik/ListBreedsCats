package com.olgazaloznaya.listbreedscats.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Breeds implements Parcelable {

    private String id;
    private String image_url;
    private String width;
    private String height;

    private String[] breeds;

    public Breeds(String id, String image_url, String width, String height, String[] breeds) {
        this.id = id;
        this.image_url = image_url;
        this.width = width;
        this.height = height;
        this.breeds = breeds;
    }

    public Breeds() {
    }

    protected Breeds(Parcel in) {
        id = in.readString();
        image_url = in.readString();
        width = in.readString();
        height = in.readString();
        breeds = in.createStringArray();
    }

    public static final Creator<Breeds> CREATOR = new Creator<Breeds>() {
        @Override
        public Breeds createFromParcel(Parcel in) {
            return new Breeds(in);
        }

        @Override
        public Breeds[] newArray(int size) {
            return new Breeds[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String[] getBreeds() {
        return breeds;
    }

    public void setBreeds(String[] breeds) {
        this.breeds = breeds;
    }

    @Override
    public String toString() {
        return "Breeds{" +
                "id='" + id + '\'' +
                ", image_url='" + image_url + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", breeds=" + Arrays.toString(breeds) +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(image_url);
        dest.writeString(width);
        dest.writeString(height);
        dest.writeStringArray(breeds);
    }
}

package com.olgazaloznaya.listbreedscats.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Breeds implements Parcelable {

    private String id;
    private String name;
    private String temperament;
    private String origin;
    private String description;
    private String life_span;
    private String wikipedia_url;

    public Breeds(String id, String name, String temperament, String origin, String description, String life_span, String wikipedia_url) {
        this.id = id;
        this.name = name;
        this.temperament = temperament;
        this.origin = origin;
        this.description = description;
        this.life_span = life_span;
        this.wikipedia_url = wikipedia_url;
    }

    public Breeds() {
    }

    protected Breeds(Parcel in) {
        id = in.readString();
        name = in.readString();
        temperament = in.readString();
        origin = in.readString();
        description = in.readString();
        life_span = in.readString();
        wikipedia_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(temperament);
        dest.writeString(origin);
        dest.writeString(description);
        dest.writeString(life_span);
        dest.writeString(wikipedia_url);
    }

    @Override
    public int describeContents() {
        return 0;
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

    @Override
    public String toString() {
        return "Breeds{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", temperament='" + temperament + '\'' +
                ", origin='" + origin + '\'' +
                ", description='" + description + '\'' +
                ", life_span='" + life_span + '\'' +
                ", wikipedia_url='" + wikipedia_url + '\'' +
                ", weight=" +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }
}

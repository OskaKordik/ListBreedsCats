package com.olgazaloznaya.listbreedscats.api.responses;

import com.olgazaloznaya.listbreedscats.model.Breeds;

import java.util.Arrays;

public class BreedsResponse {
    private Breeds[] breeds;
    private String id;
    private String url;
    private Integer width;
    private Integer height;

    @Override
    public String toString() {
        return "BreedsResponse{" +
                "breeds=" + Arrays.toString(breeds) +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public Breeds[] getBreeds() {
        return breeds;
    }

    public void setBreeds(Breeds[] breeds) {
        this.breeds = breeds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}



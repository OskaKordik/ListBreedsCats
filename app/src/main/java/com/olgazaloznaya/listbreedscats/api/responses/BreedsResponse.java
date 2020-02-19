package com.olgazaloznaya.listbreedscats.api.responses;

import com.olgazaloznaya.listbreedscats.model.Breeds;

import java.util.Arrays;

public class BreedsResponse {
    private Breeds[] breeds;
    private String id;
    private String url;

    @Override
    public String toString() {
        return "BreedsResponse{" +
                "breeds=" + Arrays.toString(breeds) +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

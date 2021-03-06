package com.olgazaloznaya.listbreedscats.api;

import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponse;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponseList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v1/breeds")
    Call<List<BreedsResponseList>> listBreeds(
//            @Query("attach_breed") Integer attach_breed,
//            @Query("limit") Integer limit,
//            @Query("page") Integer page
    );

    @GET("v1/images/search")
    Call<List<BreedsResponse>> getBreeds(
            @Query("breed_id") String breed_id
    );
}

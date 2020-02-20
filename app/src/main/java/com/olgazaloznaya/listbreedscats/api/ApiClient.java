package com.olgazaloznaya.listbreedscats.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponseList;
import com.olgazaloznaya.listbreedscats.model.Breeds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String TAG = "ApiClient";
    private static ApiClient apiClient;
    private static Retrofit retrofit;
    private MutableLiveData<List<BreedsResponseList>> mBreeds;
    private MutableLiveData<Breeds> mBreed;

    private ApiClient() {
        mBreeds = new MutableLiveData<>();
        mBreed = new MutableLiveData<>();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConstants.BASE_URL)
                .build();
    }

    public static ApiClient getInstance() {
        if (apiClient == null) apiClient = new ApiClient();
        return apiClient;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }

    public LiveData<List<BreedsResponseList>> getBreeds() {
        return mBreeds;
    }

    public LiveData<Breeds> getBreed() {
        return mBreed;
    }

    public void searchBreedsApi(){

        ApiService apiService = ApiClient.getInstance().getApiService();
        Call<List<BreedsResponseList>> responseCall = apiService.listBreeds();

        responseCall.enqueue(new Callback<List<BreedsResponseList>>() {
            @Override
            public void onResponse(Call<List<BreedsResponseList>> call, Response<List<BreedsResponseList>> response) {
                Log.d(TAG, "onResponse: server response: " + response.toString());
                if(response.code() == 200) {
                    List<BreedsResponseList> list = new ArrayList<>((response.body()));
                        mBreeds.postValue(list);

                    Log.d(TAG, "onResponse: " + response.body().toString());
                }
                else {
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                        mBreeds.postValue(null);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BreedsResponseList>> call, Throwable t) {
                Log.d(TAG, "onFailure: server response: " + t.getMessage());
                mBreeds.postValue(null);
            }
        });

    }
}

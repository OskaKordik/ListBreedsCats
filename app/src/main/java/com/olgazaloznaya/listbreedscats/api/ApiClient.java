package com.olgazaloznaya.listbreedscats.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient apiClient;
    private static Retrofit retrofit;

    private ApiClient() {
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
}

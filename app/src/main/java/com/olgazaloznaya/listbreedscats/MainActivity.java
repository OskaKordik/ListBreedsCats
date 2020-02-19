package com.olgazaloznaya.listbreedscats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.olgazaloznaya.listbreedscats.api.ApiClient;
import com.olgazaloznaya.listbreedscats.api.ApiService;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponse;
import com.olgazaloznaya.listbreedscats.model.Breeds;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
    }


    private void testRetrofitRequest() {
        ApiService apiService = ApiClient.getInstance().getApiService();
        Call<List<BreedsResponse>> responseCall = apiService.listBreeds();

        responseCall.enqueue(new Callback<List<BreedsResponse>>() {
            @Override
            public void onResponse(Call<List<BreedsResponse>> call, Response<List<BreedsResponse>> response) {
                Log.d(TAG, "onResponse: server response: " + response.toString());
                if(response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                }
                else {
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BreedsResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: server response: " + t.getMessage());
            }
        });

    }
}

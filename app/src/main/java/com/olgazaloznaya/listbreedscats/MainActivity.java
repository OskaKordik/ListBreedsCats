package com.olgazaloznaya.listbreedscats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponseList;
import com.olgazaloznaya.listbreedscats.viewmodels.BreedsViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BreedsViewModel mBreedsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBreedsViewModel = new ViewModelProvider(this).get(BreedsViewModel.class);

        subscribeObservers();

        findViewById(R.id.button_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
    }

    private void subscribeObservers() {
        mBreedsViewModel.getBreeds().observe(this, new Observer<List<BreedsResponseList>>() {
            @Override
            public void onChanged(List<BreedsResponseList> breeds) {
                if(breeds != null) {
                    for (BreedsResponseList breed : breeds) {
                        Log.d(TAG, "onChanged: " + breed.getName());
                    }
                }
            }
        });
    }

    private void testRetrofitRequest() {
        mBreedsViewModel.searchBreedsApi();
    }
}

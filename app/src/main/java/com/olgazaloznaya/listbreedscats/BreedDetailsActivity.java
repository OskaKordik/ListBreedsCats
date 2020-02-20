package com.olgazaloznaya.listbreedscats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponse;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponseList;
import com.olgazaloznaya.listbreedscats.model.Breeds;
import com.olgazaloznaya.listbreedscats.viewmodels.BreedsViewModel;

import java.util.List;

public class BreedDetailsActivity extends AppCompatActivity {

    private BreedsViewModel mBreedsViewModel;
    private ImageView breed_image;
    private TextView breed_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_details);

        breed_image = findViewById(R.id.breed_image);
        breed_name = findViewById(R.id.breed_name);

        mBreedsViewModel = new ViewModelProvider(this).get(BreedsViewModel.class);

        getIncomingIntent();
        subscribeObservers();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("breedID")){
            mBreedsViewModel.searchBreedByID(getIntent().getStringExtra("breedID"));
        }
    }

    private void subscribeObservers() {
        mBreedsViewModel.getBreed().observe(this, new Observer<List<BreedsResponse>>() {
            @Override
            public void onChanged(List<BreedsResponse> breedsResponse) {
                if (breedsResponse != null) {
                    BreedsResponse breed = breedsResponse.get(0);

                    breed_name.setText(breed.getBreeds()[0].getName());
                    breed_image.setMaxHeight(breed.getHeight());
                    breed_image.setMaxWidth(breed.getWidth());

                    Glide.with(getBaseContext())
                            .setDefaultRequestOptions(new RequestOptions())
                            .load(breed.getUrl())
                            .into(breed_image);
                }
            }
        });
    }
}

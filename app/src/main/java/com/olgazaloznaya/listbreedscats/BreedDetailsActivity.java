package com.olgazaloznaya.listbreedscats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponse;
import com.olgazaloznaya.listbreedscats.model.Breeds;
import com.olgazaloznaya.listbreedscats.viewmodels.BreedsViewModel;

import java.util.List;

public class BreedDetailsActivity extends AppCompatActivity {

    private BreedsViewModel mBreedsViewModel;
    private ImageView breed_image;
    private TextView breed_name;
    private TextView breed_description;
    private TextView breed_temperament;
    private TextView breed_origin;
    private TextView breed_life_span;
    private TextView breed_wikipedia_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_details);

        breed_image = findViewById(R.id.breed_image);
        breed_name = findViewById(R.id.breed_name);

        breed_wikipedia_url = findViewById(R.id.text_breed_wikipedia_url);
        breed_description = findViewById(R.id.text_breed_desc);
        breed_temperament = findViewById(R.id.text_breed_temp);
        breed_origin = findViewById(R.id.text_breed_origin);
        breed_life_span = findViewById(R.id.text_breed_life_span);

        getSupportActionBar().hide();

        breed_wikipedia_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse(breed_wikipedia_url.getText().toString());
                startActivity(new Intent(Intent.ACTION_VIEW, address));
            }
        });

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
                    BreedsResponse breeds = breedsResponse.get(0);
                    Breeds breed = breeds.getBreeds()[0];

                    breed_name.setText(breed.getName());
                    breed_wikipedia_url.setText(breed.getWikipedia_url());
                    breed_description.setText(breed.getDescription());
                    breed_temperament.setText(breed.getTemperament());
                    breed_origin.setText(breed.getOrigin());
                    breed_life_span.setText(breed.getLife_span().concat(" ").concat(getString(R.string.average_life_span)));

//                    breed_image.setMaxHeight(breed.getHeight());
//                    breed_image.setMaxWidth(breed.getWidth());

                    Glide.with(getBaseContext())
                            .setDefaultRequestOptions(new RequestOptions())
                            .load(breeds.getUrl())
                            .into(breed_image);
                }
            }
        });
    }
}

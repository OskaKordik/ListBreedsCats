package com.olgazaloznaya.listbreedscats.repositories;


import androidx.lifecycle.LiveData;

import com.olgazaloznaya.listbreedscats.api.ApiClient;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponse;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponseList;

import java.util.List;

public class BreedsRepository {

    private static BreedsRepository breedsRepository;
    private ApiClient mApiClient;

    public static BreedsRepository getInstance() {
        if(breedsRepository == null) breedsRepository = new BreedsRepository();
        return breedsRepository;
    }

    private BreedsRepository() {
        mApiClient = ApiClient.getInstance();

    }

    public LiveData<List<BreedsResponseList>> getBreeds() {
        return mApiClient.getBreeds();
    }

    public LiveData<List<BreedsResponse>> getBreed() {
        return mApiClient.getBreed();
    }

    public void searchBreedsApi() {
        mApiClient.searchBreedsApi();
    }

    public void searchBreedByID(String breedID) {
        mApiClient.searchBreedByID(breedID);
    }
}

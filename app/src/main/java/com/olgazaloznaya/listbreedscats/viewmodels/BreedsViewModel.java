package com.olgazaloznaya.listbreedscats.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponse;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponseList;
import com.olgazaloznaya.listbreedscats.repositories.BreedsRepository;

import java.util.List;

public class BreedsViewModel extends ViewModel {

    private BreedsRepository mBreedsRepository;

    public BreedsViewModel() {
        mBreedsRepository = BreedsRepository.getInstance();
    }

    public LiveData<List<BreedsResponseList>> getBreeds() {
        return mBreedsRepository.getBreeds();
    }

    public LiveData<List<BreedsResponse>> getBreed() {
        return mBreedsRepository.getBreed();
    }

    public void searchBreedsApi() {
        mBreedsRepository.searchBreedsApi();
    }

    public void searchBreedByID(String breedID) {
        mBreedsRepository.searchBreedByID(breedID);
    }

    public List<BreedsResponseList> getBreedsList() {
        return mBreedsRepository.getBreeds().getValue();
    }
}

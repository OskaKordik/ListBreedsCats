package com.olgazaloznaya.listbreedscats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.olgazaloznaya.listbreedscats.adapters.BreedsListAdapter;
import com.olgazaloznaya.listbreedscats.adapters.OnBreedsClickListener;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponseList;
import com.olgazaloznaya.listbreedscats.viewmodels.BreedsViewModel;
import java.util.List;

public class BreedListActivity extends AppCompatActivity implements OnBreedsClickListener {

    private static final String TAG = "BreedListActivity";
    private BreedsViewModel mBreedsViewModel;
    private RecyclerView mRecyclerView;
    private BreedsListAdapter mAdapter;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private TextView textViewNoConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBreedsViewModel = new ViewModelProvider(this).get(BreedsViewModel.class);
        mRecyclerView = findViewById(R.id.recyclerViewBreedsList);
        mNetworkChangeReceiver = NetworkChangeReceiver.getInstance();
        textViewNoConnection = findViewById(R.id.textViewNoConnection);

        initRecyclerView();
        subscribeObservers();
        testRetrofitRequest();
        initBroadcastReceiver();
    }

    private void initBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mNetworkChangeReceiver, filter);
    }

    private void initRecyclerView() {
        mAdapter = new BreedsListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void subscribeObservers() {
        mBreedsViewModel.getBreeds().observe(this, new Observer<List<BreedsResponseList>>() {
            @Override
            public void onChanged(List<BreedsResponseList> breeds) {
                if(breeds != null) {
                    for (BreedsResponseList breed : breeds) {
                        Log.d(TAG, "onChanged: Name = " + breed.getName() + ", ID = " + breed.getId());
                        mAdapter.setBreedsList(breeds);
                    }
                }
            }
        });
        mNetworkChangeReceiver.getStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    if (s.equals("NOT_CONNECTED")) {
                        mRecyclerView.setVisibility(View.GONE);
                        textViewNoConnection.setVisibility(View.VISIBLE);
                    }
                    else if (s.equals("CONNECTED")) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        textViewNoConnection.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void testRetrofitRequest() {
        mBreedsViewModel.searchBreedsApi();
    }

    @Override
    public void onBreedsClick(int position) {
        Intent intent = new Intent(this, BreedDetailsActivity.class);
        intent.putExtra("breedID", mBreedsViewModel.getBreedsList().get(position).getId());
        startActivity(intent);
    }
}

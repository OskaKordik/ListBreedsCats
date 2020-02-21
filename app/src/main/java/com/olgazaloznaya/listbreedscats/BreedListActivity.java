package com.olgazaloznaya.listbreedscats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.olgazaloznaya.listbreedscats.adapters.BreedsListAdapter;
import com.olgazaloznaya.listbreedscats.adapters.OnBreedsClickListener;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponseList;
import com.olgazaloznaya.listbreedscats.viewmodels.BreedsViewModel;
import java.util.List;

public class BreedListActivity extends AppCompatActivity implements OnBreedsClickListener {

    private BreedsViewModel mBreedsViewModel;
    private RecyclerView mRecyclerView;
    private BreedsListAdapter mAdapter;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private TextView textViewNoConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_list);

        mBreedsViewModel = new ViewModelProvider(this).get(BreedsViewModel.class);
        mRecyclerView = findViewById(R.id.recyclerViewBreedsList);
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        textViewNoConnection = findViewById(R.id.textViewNoConnection);

        initRecyclerView();
        subscribeObservers();
        searchBreedsApi();
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
                        mAdapter.setBreedsList(breeds);
                        mAdapter.notifyDataSetChanged();
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
                        searchBreedsApi();

                        mRecyclerView.setVisibility(View.VISIBLE);
                        textViewNoConnection.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void searchBreedsApi() {
        mBreedsViewModel.searchBreedsApi();
    }

    @Override
    public void onBreedsClick(int position) {
        Intent intent = new Intent(this, BreedDetailsActivity.class);
        searchBreedsApi();

        if ((mBreedsViewModel.getBreedsList() != null) && !mBreedsViewModel.getBreedsList().isEmpty()) {
            intent.putExtra("breedID", mBreedsViewModel.getBreedsList().get(position).getId());
            startActivity(intent);
        } else Toast.makeText(this, "Ошибка загрузки", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkChangeReceiver);
    }
}

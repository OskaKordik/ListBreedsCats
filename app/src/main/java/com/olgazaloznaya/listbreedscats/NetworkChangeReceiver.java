package com.olgazaloznaya.listbreedscats;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private MutableLiveData<String> status;

    public NetworkChangeReceiver() {
        status = new MutableLiveData<>();
    }

    public LiveData<String> getStatus() {
        return status;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        status.postValue(status(context));
    }

    private String status(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (null != networkInfo) {
            if((networkInfo.getType() == ConnectivityManager.TYPE_WIFI) || (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE))
                return "CONNECTED";
        }
        return "NOT_CONNECTED";
    }
}

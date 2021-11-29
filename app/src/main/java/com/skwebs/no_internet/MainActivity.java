package com.skwebs.no_internet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastReceiver = new ConnectivityReceiver();
        internetStatus();
    }

    public void internetStatus(){
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}

class ConnectivityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean status = CheckInternet.getNetworkInfo(context);
        if(status){
            Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Not Connected.", Toast.LENGTH_SHORT).show();
        }
    }
}
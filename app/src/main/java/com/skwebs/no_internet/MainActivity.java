package com.skwebs.no_internet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RelativeLayout internetLayout, noInternetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        internetLayout = findViewById(R.id.internet_layout);
        noInternetLayout = findViewById(R.id.no_internet_layout);
    }

    final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean status = CheckInternet.getNetworkInfo(context);
            if(status) {
                internetLayout.setVisibility(View.VISIBLE);
                noInternetLayout.setVisibility(View.GONE);
                Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "not connected", Toast.LENGTH_SHORT).show();
                internetLayout.setVisibility(View.GONE);
                noInternetLayout.setVisibility(View.VISIBLE);
            }
        }

    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}


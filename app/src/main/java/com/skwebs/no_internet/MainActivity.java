package com.skwebs.no_internet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RelativeLayout internetLayout, noInternetLayout;
    Button enableWiFiBtn, disableWiFiBtn, toggleWiFiBtn, checkWiFiPermissionBtn;
    WifiManager wifiManager;
    final String[] permissions = {"android.permission.ACCESS_WIFI_STATE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        internetLayout = findViewById(R.id.internet_layout);
        noInternetLayout = findViewById(R.id.no_internet_layout);

        enableWiFiBtn = findViewById(R.id.enable_wifi_btn);
        disableWiFiBtn = findViewById(R.id.disable_wifi_btn);
        toggleWiFiBtn = findViewById(R.id.toggle_wifi_btn);
        checkWiFiPermissionBtn = findViewById(R.id.check_wifi_permission_btn);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

//        enable wifi
        enableWiFiBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Clicked on enable btn", Toast.LENGTH_SHORT).show();
            if(!wifiManager.isWifiEnabled()){
                wifiManager.setWifiEnabled(true);
                Toast.makeText(this, "Clicked on enable btn INSIDE", Toast.LENGTH_SHORT).show();
            }
        });

//        disable wifi
        disableWiFiBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Clicked on disable btn", Toast.LENGTH_SHORT).show();
            if(wifiManager.isWifiEnabled()){
                wifiManager.setWifiEnabled(false);
                Toast.makeText(this, "Clicked on disable btn INSIDE", Toast.LENGTH_SHORT).show();
            }
        });


        toggleWiFiBtn.setOnClickListener(v -> {

            if(wifiManager.isWifiEnabled()){
                wifiManager.setWifiEnabled(false);
                Toast.makeText(this, "WiFi disabled", Toast.LENGTH_SHORT).show();
            } else if(!wifiManager.isWifiEnabled()){
                wifiManager.setWifiEnabled(true);
                Toast.makeText(this, "WiFi enabled", Toast.LENGTH_SHORT).show();
            }
        });


        checkWiFiPermissionBtn.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, 1);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }


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
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

}


package com.skwebs.no_internet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InternetReceiver extends BroadcastReceiver {
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

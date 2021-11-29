package com.skwebs.no_internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternet {
    public static boolean getNetworkInfo(Context context){
        boolean status;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        status = networkInfo != null;
        return status;
    }
}

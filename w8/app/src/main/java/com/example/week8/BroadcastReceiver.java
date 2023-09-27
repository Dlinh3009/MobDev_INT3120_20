package com.example.week8;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            if (isNetworkAvailable(context)) {
                Toast.makeText(context, "Internet available", Toast.LENGTH_LONG).show();
            } else Toast.makeText(context, "No internet available", Toast.LENGTH_LONG).show();
        }
//        if (checkInternet(context)) {
//            Toast.makeText(context, "Network Available Do operations", Toast.LENGTH_LONG).show();
//        }
//        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
//            Toast.makeText(context, "Boot completed", Toast.LENGTH_LONG).show();
//        }
//        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
//            Toast.makeText(context, "Connectivity changed", Toast.LENGTH_LONG).show();
//        }
    }

    boolean checkInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null){
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) {
                return false;
            }

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
//
//    private Boolean isNetworkAvailable(Application application) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            Network nw = connectivityManager.getActiveNetwork();
//            if (nw == null) return false;
//            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
//            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
//                    || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
//                    || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
//        } else {
//            NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
//            return nwInfo != null && nwInfo.isConnected();
//        }
//    }
}

package com.example.afinal;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.content.BroadcastReceiver;

public class WifiReceiver extends BroadcastReceiver {
    private String TAG="WifiReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the current network status
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        Log.d(TAG, "Listening For Wifi");

        if (activeNetwork == null || !activeNetwork.isConnected() || activeNetwork.getType() != ConnectivityManager.TYPE_WIFI) {
            // Wi-Fi is disconnected
            Log.d(TAG, "Wi-Fi is disconnected.");

            // Show an alert dialog to the user
            new AlertDialog.Builder(context)
                    .setTitle("Wi-Fi Disconnected")
                    .setMessage("You have been disconnected from Wi-Fi. Please check your connection.")
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        } else {
            // Wi-Fi is connected
            Log.d(TAG, "Wi-Fi is connected.");
        }
    }
}

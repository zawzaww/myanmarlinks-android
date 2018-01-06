package com.zawzaw.savethelibrary.utils.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.zawzaw.savethelibrary.MainApplication;

/**
 * Created by zawzaw on 06/01/18.
 */

public class ConnectionReceiver extends BroadcastReceiver {

    public static ConnectionReciverLinstener connectionReciverLinstener;

    public ConnectionReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork =  cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (connectionReciverLinstener != null) {
            connectionReciverLinstener.onNetworkConnectionChanged(isConnected);
        }
    }

    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) MainApplication.getmInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public interface ConnectionReciverLinstener {
        void onNetworkConnectionChanged(boolean isChanged);
    }

}

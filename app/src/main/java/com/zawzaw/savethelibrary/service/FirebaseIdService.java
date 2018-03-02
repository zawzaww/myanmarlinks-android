package com.zawzaw.savethelibrary.service;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by zawzaw on 30/01/18.
 */

public class FirebaseIdService extends FirebaseInstanceIdService {

    public FirebaseIdService() {

    }

    @Override
    public void onTokenRefresh() {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.i("TOKEN", refreshToken);
    }

}

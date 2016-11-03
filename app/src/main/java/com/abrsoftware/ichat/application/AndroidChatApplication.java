package com.abrsoftware.ichat.application;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by AbrWin on 03/11/16.
 */

public class AndroidChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}

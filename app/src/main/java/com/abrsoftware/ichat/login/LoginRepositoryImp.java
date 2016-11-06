package com.abrsoftware.ichat.login;

import android.util.Log;

import com.abrsoftware.ichat.domain.FireBaseHelper;

/**
 * Created by abrwin on 05/11/2016.
 */

public class LoginRepositoryImp implements LoginRepository {
    private final String TAG = LoginRepository.class.getSimpleName();
    private FireBaseHelper helper;

    public LoginRepositoryImp() {
        this.helper = FireBaseHelper.getInstance();
    }

    @Override
    public void checkSession() {
        Log.d(TAG,"checkSession");
    }

    @Override
    public void signUp(String email, String password) {
        Log.d(TAG,"signup");
    }

    @Override
    public void signIn(String email, String password) {
        Log.d(TAG,"signin");
    }
}

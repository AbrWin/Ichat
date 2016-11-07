package com.abrsoftware.ichat.login;

import android.util.Log;

import com.abrsoftware.ichat.domain.FireBaseHelper;
import com.abrsoftware.ichat.lib.Eventbus;
import com.abrsoftware.ichat.lib.GreenRobotEventBus;
import com.abrsoftware.ichat.login.eventLogin.LoginEvent;

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
        Log.d("@@"+TAG,"checkSession");
        postEvent(LoginEvent.onFailedToRecoverSession);
    }

    @Override
    public void signUp(String email, String password) {
        Log.d("@@"+TAG,"signup");
        postEvent(LoginEvent.onSignUpSuccess);

    }

    @Override
    public void signIn(String email, String password) {
        Log.d("@@"+TAG,"signin");
        postEvent(LoginEvent.onSignInSucces);
    }

    //Set only the event
    private void postEvent(int type){
        postEvent(type, null);
    }

    //Set the event type with message error
    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }

        Eventbus eventbus = GreenRobotEventBus.getInstance();
        eventbus.post(loginEvent);
    }
}

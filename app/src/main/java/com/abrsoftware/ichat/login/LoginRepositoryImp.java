package com.abrsoftware.ichat.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.domain.FireBaseHelper;
import com.abrsoftware.ichat.lib.Eventbus;
import com.abrsoftware.ichat.lib.GreenRobotEventBus;
import com.abrsoftware.ichat.login.eventLogin.LoginEvent;
import com.abrsoftware.ichat.entities.User;
import com.abrsoftware.ichat.utils.Connectivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by abrwin on 05/11/2016.
 */

public class LoginRepositoryImp implements LoginRepository {
    private final String TAG = LoginRepository.class.getSimpleName();
    private FireBaseHelper helper;
    private FirebaseAuth dataReference;
    private FirebaseUser myUserReference;

    public LoginRepositoryImp() {
        this.helper = FireBaseHelper.getInstance();
        this.dataReference = helper.getDataReference();
        this.myUserReference = helper.getDataReference().getCurrentUser();
    }

    @Override
    public void checkSession() {
        Log.d("@@" + TAG, "checkSession");
        postEvent(LoginEvent.onFailedToRecoverSession);
    }

    @Override
    public void signUp(String email, String password, Context context) {

        Log.d("@@" + TAG, "signup");
    }

    @Override
    public void signIn(String email, String password, Context context) {
        Log.d("@@" + TAG, "signin");

        //Check is network is avaliable
        if (!Connectivity.isOnline(context)) {
            postEvent(LoginEvent.onFailedConnection, context.getString(R.string.error_connection));
        }

        //Check GooglePlayServices
        if(!isGooglePlayServicesAvaliable(context)){
            return;
        }

        //Check Authentication in FireBase;
        doSignIn(email, password);

    }

    //Set only the event
    private void postEvent(int type) {
        postEvent(type, null);
    }

    //Set the event type with message error
    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }

        Eventbus eventbus = GreenRobotEventBus.getInstance();
        eventbus.post(loginEvent);
    }

    private void postEvent(int type, int status) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        loginEvent.setStatusCode(status);
        Eventbus eventbus = GreenRobotEventBus.getInstance();
        eventbus.post(loginEvent);
    }

    public boolean isGooglePlayServicesAvaliable(Context context) {
        int statusCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (GoogleApiAvailability.getInstance().isUserResolvableError(statusCode)) {
            postEvent(LoginEvent.onBeUserResolvableError, statusCode);
            return false;
        } else if (statusCode != ConnectionResult.SUCCESS) {
            postEvent(LoginEvent.onGooglePlayServicesFailed);
            return false;
        }
        return true;
    }

    private void doSignIn(String email, String password) {
        dataReference.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });
    }
}

package com.abrsoftware.ichat.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;

import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.domain.FireBaseHelper;
import com.abrsoftware.ichat.entities.User;
import com.abrsoftware.ichat.lib.Eventbus;
import com.abrsoftware.ichat.lib.GreenRobotEventBus;
import com.abrsoftware.ichat.login.eventLogin.LoginEvent;
import com.abrsoftware.ichat.utils.Connectivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by abrwin on 05/11/2016.
 */

public class LoginRepositoryImp implements LoginRepository {
    private final String TAG = LoginRepository.class.getSimpleName();
    private FireBaseHelper helper;
    private FirebaseAuth authReference;
    private FirebaseUser userReference;
    private FirebaseDatabase dataBaseReference;

    public LoginRepositoryImp() {
        this.helper = FireBaseHelper.getInstance();
        this.authReference = helper.getAuthReference();
        this.dataBaseReference = helper.getDatabaseReference();
        this.userReference = helper.getAuthReference().getCurrentUser();
    }

    @Override
    public void checkSession() {
        Log.d("@@" + TAG, "checkSession");
        if(userReference != null){
            postEvent(LoginEvent.onSignInSucces , userReference.getEmail());
        }else{
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }

    @Override
    public void signUp(String email, String password, Context context) {
        Log.d("@@" + TAG, "signup");
        //Check is network is avaliable
        if (!Connectivity.isOnline(context)) {
            postEvent(LoginEvent.onFailedConnectionNetwork, context.getString(R.string.error_connection));
        }

        //Check GooglePlayServices
        if(!isGooglePlayServicesAvaliable(context)){
            return;
        }

        if(email.trim().isEmpty()){
            postEvent(LoginEvent.onShowErrorEmail, context.getString(R.string.error_mail));
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            postEvent(LoginEvent.onShowErrorEmail, context.getString(R.string.error_mail_not_valid));
        }else if(password.trim().isEmpty()){
            postEvent(LoginEvent.onShowErrorPassword, context.getString(R.string.error_password_not_valid));
        }else{
            //Check Authentication in FireBase;
            doSignUp(email, password, context);
        }
    }

    @Override
    public void signIn(String email, String password, Context context) {
        Log.d("@@" + TAG, "signin");

        //Check is network is avaliable
        if (!Connectivity.isOnline(context)) {
            postEvent(LoginEvent.onFailedConnectionNetwork, context.getString(R.string.error_connection));
        }

        //Check GooglePlayServices
        if(!isGooglePlayServicesAvaliable(context)){
            return;
        }

        if(email.trim().isEmpty()){
            postEvent(LoginEvent.onShowErrorEmail, context.getString(R.string.error_mail));
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            postEvent(LoginEvent.onShowErrorEmail, context.getString(R.string.error_mail_not_valid));
        }else if(password.trim().isEmpty()){
            postEvent(LoginEvent.onShowErrorPassword, context.getString(R.string.error_password_not_valid));
        }else{
            //Check Authentication in FireBase;
            doSignIn(email, password, context);
        }
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

    private void doSignIn(String email, String password, final Context context) {
        authReference.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    postEvent(LoginEvent.onSignInError, context.getString(R.string.error_signin));
                }else {
                    postEvent(LoginEvent.onSignInSucces, "Inicio sesion");
                }

            }
        });
    }

    private void doSignUp(final String email, String password, final Context context){
        authReference.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    postEvent(LoginEvent.onSignInError, context.getString(R.string.error_signup));
                }else {
                    DatabaseReference mDatabase = dataBaseReference.getReference().child("users");
                    //String useriD= mDatabase.push().getKey();
                    User currentUser = new User();
                    currentUser.setEmail(email);
                    currentUser.setOnline(true);
                    String keyEmail = email.replace(".","_");
                    mDatabase.child(keyEmail).setValue(currentUser);
                    postEvent(LoginEvent.onSignInSucces, "Inicio sesion");
                }
            }
        });
    }
}

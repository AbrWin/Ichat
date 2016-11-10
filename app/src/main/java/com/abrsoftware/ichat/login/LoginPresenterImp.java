package com.abrsoftware.ichat.login;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;

import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.lib.Eventbus;
import com.abrsoftware.ichat.lib.GreenRobotEventBus;
import com.abrsoftware.ichat.login.eventLogin.LoginEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by abrwin on 05/11/2016.
 */

public class LoginPresenterImp implements LoginMvp.Presenter {
    private final String TAG = LoginPresenterImp.class.getSimpleName();
    private Eventbus eventbus;
    private LoginMvp.View loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImp(LoginMvp.View loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImp();
        this.eventbus = GreenRobotEventBus.getInstance();
    }

    @Subscribe
    @Override
    public void oncreate() {
        eventbus.register(this);
    }

    //Se asigna null a la vista para que no quede en memoria
    @Override
    public void onDestroy() {
        loginView = null;
        eventbus.unregister(this);
    }

    //Verifica si el usuario esta autenticado
    @Override
    public void checkForAuthenticatedUser() {
        if(loginView != null){
            loginView.showProgressbar(true);
            loginInteractor.checkSession();
        }
    }

    //Inicia sesion
    @Override
    public void validateLogin(String email, String password, Context context) {
        if (loginView != null) {
            loginView.showProgressbar(true);
            loginInteractor.doSignIn(email, password, context);
        }
    }

    //Registra nuevo usuario
    @Override
    public void registerNewUser(String email, String password, Context context) {
        if(loginView != null){
            if(email.trim().isEmpty()){
                loginView.setMailError(context.getString(R.string.error_mail));
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                loginView.setMailError(context.getString(R.string.error_mail_not_valid));
            }else if(password.trim().isEmpty()){
                loginView.setPasswordError(context.getString(R.string.error_password_not_valid));
            }else{
                loginView.showProgressbar(true);
                loginInteractor.doSignUp(email, password, context);
            }
        }

    }

    @Subscribe
    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSignInSucces:
                onSignInSuccess(event.getErrorMessage());
                break;
            case LoginEvent.onSignInError:
                onSignInSuccessError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSingUpSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSingUpSuccessError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedConnectionNetwork:
                onErrorConnection(event.getErrorMessage());
                break;
            case LoginEvent.onBeUserResolvableError:
                showGooglePlayServicesDialog(event.getStatusCode());
                break;
            case LoginEvent.onGooglePlayServicesFailed:
                showGooglePlayServicesError();
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
            case LoginEvent.onShowErrorEmail:
                showErrorEmail(event.getErrorMessage());
                break;
            case LoginEvent.onShowErrorPassword:
                showErrorPassword(event.getErrorMessage());
        }
    }



    private void onFailedToRecoverSession(){
        if(loginView != null){
            loginView.showProgressbar(false);
        }
        Log.d("@@"+TAG,"Error in recovery session");
    }

    private void onSignInSuccess(String msg){
        if(loginView != null){
            loginView.showProgressbar(false);
            loginView.navigateMainScree(msg);
        }
    }

    private void onSingUpSuccess(){
        if(loginView != null){
            loginView.showProgressbar(false);
            loginView.newUserSucces();
        }
    }

    private void onSignInSuccessError(String error){
        if(loginView != null){
            loginView.showProgressbar(false);
            loginView.onSingInError(error);
        }
    }

    private void onSingUpSuccessError(String error){
        if(loginView != null){
            loginView.showProgressbar(false);
            loginView.onSingUpError(error);
        }
    }

    private void onErrorConnection(String error){
        if(loginView != null){
            loginView.showProgressbar(false);
            loginView.onErrorConnection(error);
        }
    }

    private void showGooglePlayServicesError() {
        if(loginView != null){
            loginView.showProgressbar(false);
            loginView.showGooglePlayServicesError();
        }
    }

    private void showGooglePlayServicesDialog(int statusCode) {
        if(loginView != null){
            loginView.showProgressbar(false);
            loginView.showGooglePlayServicesDialog(statusCode);
        }
    }

    private void showErrorEmail(String error){
        loginView.showProgressbar(false);
        loginView.setMailError(error);
    }

    private void showErrorPassword(String error){
        loginView.showProgressbar(false);
        loginView.setPasswordError(error);
    }
}

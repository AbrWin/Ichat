package com.abrsoftware.ichat.login;

import android.content.Context;
import android.util.Patterns;

import com.abrsoftware.ichat.R;

/**
 * Created by abrwin on 05/11/2016.
 */

public class LoginPresenterImp implements LoginMvp.Presenter {
    private LoginMvp.View loginView;
    private LoginInteractor loginInteractor;
    private Context context;

    public LoginPresenterImp(LoginMvp.View loginView, Context context) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImp();
        this.context = context;
    }

    //Se asigna null a la vista para que no quede en memoria
    @Override
    public void onDestroy() {
        loginView = null;
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
    public void validateLogin(String email, String password) {
        if(loginView != null){
            loginView.showProgressbar(true);
            loginInteractor.checkSession();
        }
        loginInteractor.doSignIn(email, password);
    }

    //Registra nuevo usuario
    @Override
    public void registerNewUser(String email, String password) {
        if(loginView != null){
            if(email.trim().isEmpty()){
                loginView.setMailError(context.getString(R.string.error_mail));
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                loginView.setMailError(context.getString(R.string.error_mail_not_valid));
            }else{
                loginView.showProgressbar(true);
                loginInteractor.checkSession();
            }
        }
        loginInteractor.doSignUp(email, password);
    }


    private void onSignInSuccess(){
        if(loginView != null){
            loginView.navigateMainScree();
        }
    }

    private void onSingUpSuccess(){
        if(loginView != null){
            loginView.newUserSucces();
        }
    }

    private void onSignInSuccessError(String error){
        if(loginView != null){
            loginView.showProgressbar(false);
            loginView.newUserError(error);
        }
    }

    private void onSingUpSuccessError(){
        if(loginView != null){
            loginView.showProgressbar(false);
        }
    }

}

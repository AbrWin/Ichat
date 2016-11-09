package com.abrsoftware.ichat.login;

import android.content.Context;

/**
 * Created by abrwin on 05/11/2016.
 */

public class LoginInteractorImp implements LoginInteractor {
    private LoginRepository loginRepository;

    public LoginInteractorImp(){
        loginRepository = new LoginRepositoryImp();
    }

    @Override
    public void checkSession() {
        loginRepository.checkSession();
    }

    @Override
    public void doSignUp(String email, String password, Context context) {
        loginRepository.signUp(email, password, context);
    }

    @Override
    public void doSignIn(String email, String password, Context context) {
        loginRepository.signIn(email, password, context);
    }
}

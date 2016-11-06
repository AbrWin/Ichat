package com.abrsoftware.ichat.login;

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
    public void doSignUp(String email, String password) {
        loginRepository.signUp(email, password);
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }
}

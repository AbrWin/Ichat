package com.abrsoftware.ichat.login;

/**
 * Created by AbrWin on 04/11/16.
 */

public interface LoginRepository {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}

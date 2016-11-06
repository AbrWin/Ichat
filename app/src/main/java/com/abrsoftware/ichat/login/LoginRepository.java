package com.abrsoftware.ichat.login;

/**
 * Created by AbrWin on 04/11/16.
 */

public interface LoginRepository {
    void checkSession();
    void signUp(String email, String password);
    void signIn(String email, String password);
}

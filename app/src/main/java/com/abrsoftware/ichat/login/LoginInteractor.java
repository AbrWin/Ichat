package com.abrsoftware.ichat.login;

import android.content.Context;

/**
 * Created by AbrWin on 04/11/16.
 */

public interface LoginInteractor {
    //Unica clase que esta entereda que se usa FireBase
    void checkSession();
    void doSignUp(String email, String password, Context context);
    void doSignIn(String email, String password, Context context);
}

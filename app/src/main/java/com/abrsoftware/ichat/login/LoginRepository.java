package com.abrsoftware.ichat.login;

import android.content.Context;

/**
 * Created by AbrWin on 04/11/16.
 */

public interface LoginRepository {
    void checkSession();
    void signUp(String email, String password, Context context);
    void signIn(String email, String password, Context context);
}

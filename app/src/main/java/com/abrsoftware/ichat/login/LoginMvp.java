package com.abrsoftware.ichat.login;

import android.content.Context;

import com.abrsoftware.ichat.login.eventLogin.LoginEvent;

/**
 * Created by AbrWin on 04/11/16.
 */

public interface LoginMvp {
    interface View {
        void showProgressbar(boolean show);

        void handleSignUp();

        void handleSignIn();

        void navigateMainScree();

        void setMailError(String error);

        void setPasswordError(String error);

        void newUserSucces();

        void onSingInError(String error);

        void onSingUpError(String error);
    }

    interface Presenter {
        void oncreate();

        void onDestroy();

        void checkForAuthenticatedUser();

        void validateLogin(String email, String password);

        void registerNewUser(String email, String password);

        void onEventMainThread(LoginEvent event);
    }

}

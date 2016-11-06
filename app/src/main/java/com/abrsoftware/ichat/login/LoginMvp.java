package com.abrsoftware.ichat.login;

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

        void onSingnError(String error);

        void newUserError(String error);
    }

    interface Presenter {
        void onDestroy();

        void checkForAuthenticatedUser();

        void validateLogin(String email, String password);

        void registerNewUser(String email, String password);
    }

}

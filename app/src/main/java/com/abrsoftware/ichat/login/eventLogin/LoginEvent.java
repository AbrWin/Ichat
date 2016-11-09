package com.abrsoftware.ichat.login.eventLogin;

/**
 * Created by abrwin on 06/11/2016.
 */

public class LoginEvent {
    public final static int onSignInError = 0;
    public final static int onSignUpError = 1;
    public final static int onSignInSucces = 2;
    public final static int onSignUpSuccess = 3;
    public final static int onFailedToRecoverSession = 4;
    public final static int onFailedConnection = 5;
    public final static int onBeUserResolvableError = 6;
    public final static int onGooglePlayServicesFailed = 7;

    private int eventType;
    private String errorMessage;
    private int statusCode;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}

package com.abrsoftware.ichat.contact;

/**
 * Created by AbrWin on 22/11/16.
 */

public interface ContactSessionInteractor {
    void singOff();
    String getCurrentUserMail();
    void changeConnectionStatus(boolean status);
}

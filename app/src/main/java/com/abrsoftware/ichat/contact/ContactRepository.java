package com.abrsoftware.ichat.contact;

/**
 * Created by AbrWin on 22/11/16.
 */

public interface ContactRepository {
    void singOff();
    String getCurrentUserMail(String email);
    void changeConnectionStatus(boolean status);
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String mail);
}

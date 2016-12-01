package com.abrsoftware.ichat.contact;

/**
 * Created by AbrWin on 22/11/16.
 */

public interface ContactListRepository {
    void signOff();
    String getCurrentUserMail();
    void changeConnectionStatus(boolean status);
    void subscribeContactLisctEvent();
    void unsubscribeContactLisctEvent();
    void destroyListener();
    void removeContact(String mail);
}

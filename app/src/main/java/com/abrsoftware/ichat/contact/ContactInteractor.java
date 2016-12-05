package com.abrsoftware.ichat.contact;

/**
 * Created by AbrWin on 22/11/16.
 */

public interface ContactInteractor {
    void subscribeContactLisctEvent();
    void unsubscribeContactLisctEvent();
    void destroyListener();
    void removeContact(String mail);
}

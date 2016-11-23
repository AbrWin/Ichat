package com.abrsoftware.ichat.contact;

/**
 * Created by AbrWin on 22/11/16.
 */

public interface ContactInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String mail);
}

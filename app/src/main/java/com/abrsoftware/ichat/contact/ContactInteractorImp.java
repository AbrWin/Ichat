package com.abrsoftware.ichat.contact;

import com.abrsoftware.ichat.contact.viewContact.ContactRepoImp;

/**
 * Created by AbrWin on 25/11/16.
 */

public class ContactInteractorImp implements ContactInteractor {
    ContactRepository contactRepository;

    public ContactInteractorImp(){
        contactRepository = new ContactRepoImp();
    }

    @Override
    public void subscribeContactLisctEvent() {
        contactRepository.subscribeContactLisctEvent();
    }

    @Override
    public void unsubscribeContactLisctEvent() {
        contactRepository.unsubscribeContactLisctEvent();
    }

    @Override
    public void destroyListener() {
        contactRepository.destroyListener();
    }

    @Override
    public void removeContact(String mail) {
        contactRepository.removeContact(mail);
    }
}

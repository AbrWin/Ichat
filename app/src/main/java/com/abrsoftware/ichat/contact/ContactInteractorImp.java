package com.abrsoftware.ichat.contact;

import com.abrsoftware.ichat.contact.viewContact.ContactListRepositoryImp;

/**
 * Created by AbrWin on 25/11/16.
 */

public class ContactInteractorImp implements ContactInteractor {
    ContactListRepository contactListRepository;

    public ContactInteractorImp(){
        contactListRepository = new ContactListRepositoryImp();
    }

    @Override
    public void subscribeContactLisctEvent() {
        contactListRepository.subscribeContactLisctEvent();
    }

    @Override
    public void unsubscribeContactLisctEvent() {
        contactListRepository.unsubscribeContactLisctEvent();
    }

    @Override
    public void destroyListener() {
        contactListRepository.destroyListener();
    }

    @Override
    public void removeContact(String mail) {
        contactListRepository.removeContact(mail);
    }
}

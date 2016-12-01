package com.abrsoftware.ichat.contact;

import com.abrsoftware.ichat.contact.viewContact.ContactListRepositoryImp;

/**
 * Created by AbrWin on 25/11/16.
 */

public class ContactSessionInteractorImp implements ContactSessionInteractor {
    ContactListRepository contactListRepository;

    public ContactSessionInteractorImp() {
        contactListRepository = new ContactListRepositoryImp();
    }

    @Override
    public void singOff() {
        contactListRepository.signOff();
    }

    @Override
    public String getCurrentUserMail() {
        return contactListRepository.getCurrentUserMail();
    }

    @Override
    public void changeConnectionStatus(boolean status) {
        contactListRepository.changeConnectionStatus(status);
    }
}

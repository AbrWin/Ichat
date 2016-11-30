package com.abrsoftware.ichat.contact;

import com.abrsoftware.ichat.contact.viewContact.ContactRepoImp;

/**
 * Created by AbrWin on 25/11/16.
 */

public class ContactSessionInteractorImp implements ContactSessionInteractor {
    ContactRepository contactRepository;

    public ContactSessionInteractorImp() {
        contactRepository = new ContactRepoImp();
    }

    @Override
    public void singOff() {
        contactRepository.signOff();
    }

    @Override
    public String getCurrentUserMail() {
        return contactRepository.getCurrentUserMail();
    }

    @Override
    public void changeConnectionStatus(boolean status) {
        contactRepository.changeConnectionStatus(status);
    }
}

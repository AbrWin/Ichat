package com.abrsoftware.ichat.addcontact;

import com.abrsoftware.ichat.addcontact.viewdialog.AddContactRepositoryImp;

/**
 * Created by AbrWin on 06/12/16.
 */

public class AddContactInteractorImp implements AddContacInteractor {
    private  AddContactRepository repository;

    public AddContactInteractorImp(){
        repository = new AddContactRepositoryImp();
    }
    @Override
    public void execute(String mail) {
        repository.addContact(mail);
    }
}

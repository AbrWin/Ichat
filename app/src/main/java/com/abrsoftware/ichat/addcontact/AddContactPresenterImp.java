package com.abrsoftware.ichat.addcontact;

import com.abrsoftware.ichat.addcontact.eventaddcontact.AddContactEvent;
import com.abrsoftware.ichat.lib.Eventbus;
import com.abrsoftware.ichat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by abrwin on 04/12/2016.
 */

public class AddContactPresenterImp implements AddContactMVP.Presenter {
    private AddContactMVP.View addContactView;
    private Eventbus eventbus;
    private AddContacInteractor addContacInteractor;

    public AddContactPresenterImp(AddContactMVP.View view) {
        this.addContactView = view;
        eventbus = GreenRobotEventBus.getInstance();
        addContacInteractor = new AddContactInteractorImp();
    }

    @Override
    public void onShow() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        addContactView = null;
        eventbus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if(addContactView != null){
            addContactView.hideInput(true);
        }
        addContacInteractor.execute(email);
    }

    @Subscribe
    @Override
    public void onEventMainThread(AddContactEvent event) {
        if(addContactView != null){
            addContactView.showInput(true);

            if(event.isError()){
                addContactView.contactErrorAdded();
            }else {
                addContactView.contactAdded();
            }
        }
    }
}

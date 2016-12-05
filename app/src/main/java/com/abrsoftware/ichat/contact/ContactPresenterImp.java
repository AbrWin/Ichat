package com.abrsoftware.ichat.contact;

import com.abrsoftware.ichat.contact.eventcontact.ContactListEvent;
import com.abrsoftware.ichat.entities.User;
import com.abrsoftware.ichat.lib.Eventbus;
import com.abrsoftware.ichat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by AbrWin on 24/11/16.
 */

public class ContactPresenterImp implements ContactMvp.Presenter {
    private Eventbus eventbus;
    private ContactMvp.View contactView;
    private ContactInteractor contactInteractor;
    private ContactSessionInteractor sessionInteractor;

    public ContactPresenterImp(ContactMvp.View contactView) {
        this.contactView = contactView;
        eventbus = GreenRobotEventBus.getInstance();
        this.contactInteractor = new ContactInteractorImp();
        this.sessionInteractor = new ContactSessionInteractorImp();
    }

    @Override
    public void onCreate() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        contactView = null;
        eventbus.unregister(this);
        contactInteractor.destroyListener();
    }

    @Override
    public void onPause() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactInteractor.unsubscribeContactLisctEvent();
    }

    @Override
    public void onResume() {
        sessionInteractor.changeConnectionStatus(User.ONLINE);
        contactInteractor.subscribeContactLisctEvent();
    }

    @Override
    public String getCurrentUserEmail() {
        return sessionInteractor.getCurrentUserMail();
    }

    @Override
    public void signOff() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactInteractor.unsubscribeContactLisctEvent();
        contactInteractor.destroyListener();
        sessionInteractor.singOff();
    }

    @Override
    public void removeContact(String email) {
        contactInteractor.removeContact(email);
    }

    @Subscribe
    @Override
    public void onEventMainThread(ContactListEvent event) {
        switch (event.getEventType()){
            case ContactListEvent.onContacAdded:
                onContacAdded(event.getUser());
                break;
            case ContactListEvent.onContacChanged:
                onContacChanged(event.getUser());
                break;
            case ContactListEvent.onContacRemove:
                onContacRemove(event.getUser());
                break;
        }

    }

    private void onContacAdded(User user){
        if(contactView != null){
            contactView.onContactAdded(user);
        }
    }

    private void onContacChanged(User user){
        if(contactView != null){
            contactView.onContactChanged(user);
        }
    }

    private void onContacRemove(User user){
        if(contactView != null){
            contactView.onContactRemove(user);
        }
    }
}

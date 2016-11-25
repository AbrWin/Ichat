package com.abrsoftware.ichat.contact;

import com.abrsoftware.ichat.contact.eventcontact.ContactEvent;
import com.abrsoftware.ichat.lib.Eventbus;
import com.abrsoftware.ichat.lib.GreenRobotEventBus;

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
        this.eventbus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public String getCurrentUserEmail() {
        return null;
    }

    @Override
    public void signOff() {

    }

    @Override
    public void removeContact(String email) {

    }

    @Override
    public void onEventMainThread(ContactEvent event) {

    }
}

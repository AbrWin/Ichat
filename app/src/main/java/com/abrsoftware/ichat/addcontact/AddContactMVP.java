package com.abrsoftware.ichat.addcontact;

import com.abrsoftware.ichat.addcontact.eventaddcontact.AddContactEvent;

/**
 * Created by abrwin on 04/12/2016.
 */

public class AddContactMVP {
    public interface View{
        void showInput(boolean show);
        void hideInput(boolean hide);

        void contactAdded();
        void contactErrorAdded();
    }

    public interface Presenter{
        void onShow();
        void onDestroy();

        void addContact(String email);
        void onEventMainThread(AddContactEvent event);
    }
}

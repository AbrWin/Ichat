package com.abrsoftware.ichat.contact;

import com.abrsoftware.ichat.contact.eventcontact.ContactListEvent;
import com.abrsoftware.ichat.entities.User;

/**
 * Created by AbrWin on 22/11/16.
 */

public class ContactMvp {

    public interface View {
        void onContactAdded(User user);
        void onContactChanged(User user);
        void onContactRemove(User user);
    }

    public interface Presenter {
        void onCreate();
        void onDestroy();
        void onPause();
        void onResume();
        String getCurrentUserEmail();
        void signOff();
        void removeContact(String email);
        void onEventMainThread(ContactListEvent event);
    }

}

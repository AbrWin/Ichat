package com.abrsoftware.ichat.contact;

import android.text.TextUtils;

import com.abrsoftware.ichat.contact.eventcontact.ContactListEvent;
import com.abrsoftware.ichat.domain.FireBaseHelper;
import com.abrsoftware.ichat.entities.User;
import com.abrsoftware.ichat.lib.Eventbus;
import com.abrsoftware.ichat.lib.GreenRobotEventBus;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by AbrWin on 29/11/16.
 */

public class ContactListRepositoryImp implements ContactListRepository {
    private Eventbus eventbus;
    private FireBaseHelper helper;
    private FirebaseUser userReference;
    private ChildEventListener contactEventListener;


    public ContactListRepositoryImp() {
        this.eventbus = GreenRobotEventBus.getInstance();
        this.helper = FireBaseHelper.getInstance();
        this.userReference = helper.getAuthReference().getCurrentUser();
    }


    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentUserMail() {
        return userReference.getEmail();
    }

    @Override
    public void changeConnectionStatus(boolean status) {

    }

    @Override
    public void subscribeContactLisctEvent() {
        if(contactEventListener == null){
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContacAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContacChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContacRemove);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    //Move a branch to other
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //Cancel requisition
                }
            };
        }

        helper.getMyContactReference().addChildEventListener(contactEventListener);

    }

    @Override
    public void unsubscribeContactLisctEvent() {
        if(contactEventListener != null){
            helper.getMyContactReference().removeEventListener(contactEventListener);
        }
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void removeContact(String mail) {
        String currentUserEmail = userReference.getEmail();
        if (!TextUtils.isEmpty(currentUserEmail) && helper.getOneContactReference(currentUserEmail, mail) != null) {
            //Remove from myUser
            helper.getOneContactReference(currentUserEmail, mail).removeValue();
            //Remove from other user
            helper.getOneContactReference(mail, currentUserEmail).removeValue();
        }
    }


    private void handleContact(DataSnapshot dataSnapshot, int type){
        String email = dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online= Boolean.parseBoolean(dataSnapshot.getValue().toString());
        User user = new User();
        user.setEmail(email);
        user.setOnline(online);
        post(type, user);

    }

    private void post(int onContacAdded, User user) {
        ContactListEvent contactListEvent = new ContactListEvent();
        contactListEvent.setEventType(onContacAdded);
        contactListEvent.setUser(user);
        eventbus.post(contactListEvent);
    }

}

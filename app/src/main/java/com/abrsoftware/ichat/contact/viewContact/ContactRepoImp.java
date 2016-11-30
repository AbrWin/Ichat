package com.abrsoftware.ichat.contact.viewContact;

import android.text.TextUtils;

import com.abrsoftware.ichat.contact.ContactRepository;
import com.abrsoftware.ichat.domain.FireBaseHelper;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by AbrWin on 29/11/16.
 */

public class ContactRepoImp implements ContactRepository {
    private FireBaseHelper helper;
    private FirebaseUser userReference;
    private ChildEventListener contactEventListener;


    public ContactRepoImp() {
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

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
        }
    }

    @Override
    public void unsubscribeContactLisctEvent() {
        if(contactEventListener != null){

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
}

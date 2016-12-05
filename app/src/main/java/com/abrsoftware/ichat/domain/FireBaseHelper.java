package com.abrsoftware.ichat.domain;

import android.text.TextUtils;
import android.util.Log;

import com.abrsoftware.ichat.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AbrWin on 04/11/16.
 */

public class FireBaseHelper {
    private FirebaseAuth authReference;
    private FirebaseDatabase databaseReference;
    private String SEPARATOR = "__";
    private String CHATS_PATH = "chats";
    private String USERS_PATH = "users";
    private String CONTACTS_PATH = "contacts";


    private static class SinglentonHolder {
        private static final FireBaseHelper INSTANCE = new FireBaseHelper();
    }

    public static FireBaseHelper getInstance() {
        return SinglentonHolder.INSTANCE;
    }

    public FireBaseHelper() {
        this.authReference = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance();
    }

    public FirebaseAuth getAuthReference() {
        return authReference;
    }

    public FirebaseDatabase getDatabaseReference() {
        return databaseReference;
    }

    /**
     * This method get reference from USER
     * @return DataReference
     */
    public DatabaseReference getMyUserDataReference(){
        return getUserReference(authReference.getCurrentUser().getEmail());
    }

    /**
     * This method get one reference from child CONTACTS
     * @param mainMail
     * @param childMail
     * @return
     */
    public DatabaseReference getOneContactReference(String mainMail, String childMail) {
        String childKey = childMail.replace(".", "_");
        return getUserReference(mainMail).child(childKey);
    }

    /**
     * This method get reference from database users-->abr@_7.com
     * @param keyMail
     * @return
     */
    public DatabaseReference getUserReference(String keyMail) {
        DatabaseReference referenceUser = null;
        if (!TextUtils.isEmpty(keyMail)) {
            String mail = keyMail.replace(".", "_");
            referenceUser = databaseReference.getReference(USERS_PATH).child(mail).child(CONTACTS_PATH);
        }
        return referenceUser;
    }

    /**
     * Get all dataReference from Database Firebase
     * @param reference
     */
    public void getDataReference(String reference) {
        databaseReference.getReference(USERS_PATH).orderByChild(USERS_PATH).equalTo(reference).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().setValue(null);
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("TodoApp", "getUser:onCancelled", databaseError.toException());
                    }
                });

    }

    public void signOff() {
        authReference.signOut();
    }


}

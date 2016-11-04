package com.abrsoftware.ichat.helper;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.utilities.ParsedUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AbrWin on 04/11/16.
 */

public class FireBaseHelper {
    private Firebase dataReference;
    private final static String SEPARATOR = "__";
    private final static String USERS_PATH = "users";
    private final static String CONTACS_PATH = "contacts";
    private final static String CHATS_PATH = "chats";
    private final static String FIREBASE_URL = "https://ichat-84f67.firebaseio.com/";

    private static class SinglentonHolder {
        private static final FireBaseHelper INSTANCE = new FireBaseHelper();
    }

    public static FireBaseHelper getInstance() {
        return SinglentonHolder.INSTANCE;
    }

    public FireBaseHelper() {
        this.dataReference = new Firebase(FIREBASE_URL);
    }

    public Firebase getDataReference() {
        return dataReference;
    }

    public String getAuthUserEmail() {
        AuthData authData = dataReference.getAuth();
        String mail = null;
        if (authData != null) {
            Map<String, Object> providerData = authData.getProviderData();
            mail = providerData.get("email").toString();
        }
        return mail;
    }

    public Firebase getUserReference(String email) {
        Firebase userReference = null;
        if (email != null) {
            String emaiKey = email.replace(",", "_");
            userReference = dataReference.getRoot().getRoot().child(USERS_PATH).child(emaiKey);
        }
        return userReference;
    }


    public Firebase getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    public Firebase getContactsReference(String mail){
        return getUserReference(mail).child(CONTACS_PATH);
    }

    public Firebase getMyContacsReference(){
        return getContactsReference(getAuthUserEmail());
    }

    public Firebase getOneContactReference(String mainEmail, String childMail){
        String childKey = childMail.replace(".","_");
        return getUserReference(mainEmail).child(CONTACS_PATH).child(childKey);
    }

    public Firebase getChatReference(String receiver){
        String keySender = getAuthUserEmail().replace(".",";");
        String keyReciber = receiver.replace(".","_");

        String keyChat = keySender + SEPARATOR + keyReciber;
        if(keySender.compareTo(keyReciber) > 0){
            keyChat = keyReciber +SEPARATOR + keySender;
        }
        return dataReference.getRoot().child(CHATS_PATH).child(keyChat);
    }

    public void changeUserConnectionState(boolean online){
        if(getMyUserReference() != null){
            Map<String, Object> update = new HashMap<String, Object>();
            update.put("online", online);
            getMyUserReference().updateChildren(update);
        }
    }

    public void notifyContactsOfConnectionChange(boolean online){
        notifyContactsOfConnectionChange(online, false);
    }

    public void signOff(){
        notifyContactsOfConnectionChange(false, true);
    }

    private void notifyContactsOfConnectionChange(final boolean online, final boolean signOff){
        final String myEmail = getAuthUserEmail();
        getMyContacsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    String mail = child.getKey();
                    Firebase reference = getOneContactReference(mail, myEmail);
                    reference.setValue(online);
                }
                if(signOff){
                    dataReference.unauth();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println(firebaseError.getMessage());
            }
        });
    }
}

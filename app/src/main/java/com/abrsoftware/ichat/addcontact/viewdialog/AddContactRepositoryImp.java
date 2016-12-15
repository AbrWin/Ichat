package com.abrsoftware.ichat.addcontact.viewdialog;

import com.abrsoftware.ichat.addcontact.AddContactRepository;
import com.abrsoftware.ichat.addcontact.eventaddcontact.AddContactEvent;
import com.abrsoftware.ichat.domain.FireBaseHelper;
import com.abrsoftware.ichat.entities.User;
import com.abrsoftware.ichat.lib.Eventbus;
import com.abrsoftware.ichat.lib.GreenRobotEventBus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by AbrWin on 06/12/16.
 */

public class AddContactRepositoryImp implements AddContactRepository {
    private Eventbus eventbus;
    private FireBaseHelper helper;

    public AddContactRepositoryImp() {
        eventbus = GreenRobotEventBus.getInstance();
        helper = FireBaseHelper.getInstance();
    }

    @Override
    public void addContact(String email) {
        final String keyEmail = email.replace(".","_");
        helper.getMyContactReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null){
                    //Add to my reference
                    DatabaseReference myContactReference = helper.getMyContactReference();
                    myContactReference.child(keyEmail).setValue(true);

                    //Add to other user reference
                    String currentReference = helper.getAuthReference().getCurrentUser().getEmail();
                    currentReference = currentReference.replace(".","_");
                    DatabaseReference reverseContactReference = helper.getContacReference(keyEmail);
                    reverseContactReference.child(currentReference).setValue(user.ONLINE);
                    post(false);
                }else {
                    post(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                post(true);
            }
        });
    }

    private void post(boolean error) {
        AddContactEvent event = new AddContactEvent();
        event.setError(error);
        eventbus.post(event);
    }
}

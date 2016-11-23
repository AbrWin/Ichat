package com.abrsoftware.ichat.domain;

import com.abrsoftware.ichat.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AbrWin on 04/11/16.
 */

public class FireBaseHelper {
    private FirebaseAuth authReference;
    private FirebaseDatabase databaseReference;

    private static class SinglentonHolder {
        private static final FireBaseHelper INSTANCE = new FireBaseHelper();
    }

    public static FireBaseHelper getInstance() {
        return SinglentonHolder.INSTANCE;
    }

    public FireBaseHelper() {
        this.authReference  = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance();
    }

    public FirebaseAuth getAuthReference() {
        return authReference;
    }

    public FirebaseDatabase getDatabaseReference() {
        return databaseReference;
    }


}

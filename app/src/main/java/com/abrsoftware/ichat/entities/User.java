package com.abrsoftware.ichat.entities;

import java.util.Map;

/**
 * Created by abrwin on 07/11/2016.
 */

public class User {
    String email;
    boolean online;
    Map<String, Boolean> contacts;
    public static boolean ONLINE = true;
    public static boolean OFFLINE = false;

    public User() {
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}

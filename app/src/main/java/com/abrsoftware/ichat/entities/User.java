package com.abrsoftware.ichat.entities;

import java.util.Map;

/**
 * Created by abrwin on 07/11/2016.
 */

public class User {
    String email;
    boolean online;
    Map<String, Boolean> contacts;
    String urlImge;
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

    public String getUrlImge() {
        return urlImge;
    }

    public void setUrlImge(String urlImge) {
        this.urlImge = urlImge;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;
        if(obj instanceof User){
            User recipe = (User)obj;
            equal = this.email.equals(recipe.getEmail());
        }
        return equal;
    }
}

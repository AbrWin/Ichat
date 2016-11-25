package com.abrsoftware.ichat.contact.eventcontact;

import com.abrsoftware.ichat.entities.User;

/**
 * Created by AbrWin on 22/11/16.
 */
public class ContactEvent {
    public final static int onContacAdded = 0;
    public final static int onContacChanged = 1;
    public final static int onContacRemove = 2;

    private User user;
    private int eventType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}

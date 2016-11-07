package com.abrsoftware.ichat.lib;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by abrwin on 06/11/2016.
 */

public class GreenRobotEventBus implements Eventbus {
    EventBus eventBus;

    private static class SingletonHoder{
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance(){
        return SingletonHoder.INSTANCE;
    }

    public GreenRobotEventBus() {
        this.eventBus = EventBus.getDefault();
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}

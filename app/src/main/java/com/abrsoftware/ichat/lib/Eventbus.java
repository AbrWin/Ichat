package com.abrsoftware.ichat.lib;

/**
 * Created by abrwin on 06/11/2016.
 */

public interface Eventbus {
    //Registra a el bus a traves de un objeto
    void register(Object subscriber);
    //Quita registro a el bus a traves de un objeto
    void unregister(Object subscriber);
    //Publica evento
    void post(Object event);
}

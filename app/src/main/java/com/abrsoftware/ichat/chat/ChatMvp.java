package com.abrsoftware.ichat.chat;

import com.abrsoftware.ichat.entities.ChatMesagge;

/**
 * Created by AbrWin on 09/12/16.
 */

public class ChatMvp {

    public interface View {
        void onMessageRecived(ChatMesagge chat);
    }

    public interface Presenter {
        void onPuase();

        void onResume();

        void onCreate();

        void onDestroy();
    }

}

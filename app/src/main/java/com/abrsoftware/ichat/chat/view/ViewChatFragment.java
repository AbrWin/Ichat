package com.abrsoftware.ichat.chat.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abrsoftware.ichat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewChatFragment extends Fragment {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    public ViewChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, rootView);
        String tittle = "";
        if(getArguments() != null){
            tittle = getArguments().getString("tittle");
        }
        setToolbar(tittle);
        return rootView;
    }

    public void setToolbar(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

}

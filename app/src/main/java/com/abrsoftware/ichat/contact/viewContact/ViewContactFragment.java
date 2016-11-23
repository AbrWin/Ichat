package com.abrsoftware.ichat.contact.viewContact;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.contact.ContactMvp;
import com.abrsoftware.ichat.entities.User;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewContactFragment extends Fragment implements ContactMvp.View {
    private ContactMvp.Presenter contactPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.addContact)
    FloatingActionButton addContact;
    @BindView(R.id.reciclador)
    RecyclerView recyclerContact;
    @BindView(R.id.empty_contacts_layout)
    LinearLayout emptyContacts;


    public ViewContactFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, rootView);
        setToolbar(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setToolbar(View rootView) {
        toolbar.setTitle(getString(R.string.title_home));
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onContactAdded(User user) {

    }

    @Override
    public void onContactChanged(User user) {

    }

    @Override
    public void onContactRemove(User user) {

    }
}

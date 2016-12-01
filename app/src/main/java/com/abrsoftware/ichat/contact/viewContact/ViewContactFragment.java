package com.abrsoftware.ichat.contact.viewContact;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.adapters.ContactListAdapter;
import com.abrsoftware.ichat.contact.ContactMvp;
import com.abrsoftware.ichat.contact.ContactPresenterImp;
import com.abrsoftware.ichat.entities.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ViewContactFragment extends Fragment implements ContactMvp.View, ContactListAdapter.onItemClickListener {
    private ContactMvp.Presenter contactPresenter;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.reciclador)
    public RecyclerView recyclerContact;

    @BindView(R.id.empty_contacts_layout)
    public LinearLayout emptyContacts;

    private ContactListAdapter contactListAdapter;

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
        contactPresenter = new ContactPresenterImp(this);
        contactPresenter.onCreate();
        setToolbar(rootView, contactPresenter.getCurrentUserEmail());
        setAdapter();
        setRecycler();
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

    @Override
    public void onResume() {
        super.onResume();
        contactPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        contactPresenter.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        contactPresenter.onDestroy();
    }

    @Override
    public void onContactAdded(User user) {
        contactListAdapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        contactListAdapter.update(user);
    }

    @Override
    public void onContactRemove(User user) {
        contactListAdapter.remove(user);
    }

    @OnClick(R.id.addContact)
    public void addContact() {
        Log.d("msj", "add");
    }


    @Override
    public void onClick(ContactListAdapter.ContactHolder contactHolder) {
        contactHolder.contact.getEmail();
    }

    @Override
    public void onLongClick() {

    }

    public void setToolbar(View rootView, String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void setAdapter(){
         contactListAdapter = new ContactListAdapter(new ArrayList<User>(), this);

    }

    private void setRecycler( ) {
        emptyContacts.setVisibility(View.GONE);
        recyclerContact.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerContact.setAdapter(contactListAdapter);
    }
}

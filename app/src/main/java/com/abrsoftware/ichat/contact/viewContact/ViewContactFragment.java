package com.abrsoftware.ichat.contact.viewContact;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.adapters.ContactListAdapter;
import com.abrsoftware.ichat.addcontact.viewdialog.AddContactFragment;
import com.abrsoftware.ichat.chat.view.ViewChatFragment;
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

    @BindView(R.id.frameLayout)
    public FrameLayout frameLayout;

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
        getActivity().setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setToolbar(contactPresenter.getCurrentUserEmail());
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
        if(contactListAdapter.getItemCount() > 0){
            emptyContacts.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
        }else {
            frameLayout.setVisibility(View.GONE);
            emptyContacts.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onContactChanged(User user) {
        contactListAdapter.update(user);
    }

    @Override
    public void onContactRemove(User user) {
        contactListAdapter.remove(user);
        if(contactListAdapter.getItemCount() == 0){
            frameLayout.setVisibility(View.GONE);
            emptyContacts.setVisibility(View.VISIBLE);
        }else {
            emptyContacts.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.addContact)
    public void addContact() {
       new AddContactFragment().show(getActivity().getSupportFragmentManager(), getString(R.string.add_mesagge_tittle));
    }


    @Override
    public void onClick(ContactListAdapter.ContactHolder contactHolder) {
        Log.d("onClick", contactHolder.contact.getEmail());
        FragmentTransaction  fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
        ViewChatFragment chatFragment = new ViewChatFragment();

        Bundle bundle = new Bundle();
        bundle.putString("tittle", contactHolder.contact.getEmail());

        chatFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, chatFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onLongClick(ContactListAdapter.ContactHolder contactHolder) {
        contactPresenter.removeContact(contactHolder.contact.getEmail());
    }

    public void setToolbar(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void setRecycler( ) {
        frameLayout.setVisibility(View.VISIBLE);
        contactListAdapter = new ContactListAdapter(new ArrayList<User>(), this);
        recyclerContact.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerContact.setAdapter(contactListAdapter);
        contactListAdapter.notifyDataSetChanged();
    }
}

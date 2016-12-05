package com.abrsoftware.ichat.addcontact.viewdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.addcontact.AddContactMVP;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddContactFragment extends DialogFragment implements AddContactMVP.View{

    @BindView(R.id.wrappe_email)
    TextInputLayout wrappe_email;

    @BindView(R.id.input_email)
    EditText input_email;

    @BindView(R.id.addContactProgressbar)
    ProgressBar addContactProgressbar;


    public AddContactFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.fragment_contact, null, false);
        ButterKnife.bind(this,rootView);

        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        builder.setView(rootView);
        AlertDialog dialog = builder.create();
        return dialog;
    }


    @Override
    public void showInput(boolean show) {
        input_email.setVisibility(show ? View.VISIBLE: View.GONE);
        addContactProgressbar.setVisibility(show ? View.GONE: View.VISIBLE);
    }

    @Override
    public void hideInput(boolean hide) {
        input_email.setVisibility(hide ? View.GONE: View.VISIBLE);
        addContactProgressbar.setVisibility(hide ? View.VISIBLE: View.GONE);
    }

    @Override
    public void contactAdded() {

    }

    @Override
    public void contactErrorAdded() {

    }
}

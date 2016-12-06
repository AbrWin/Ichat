package com.abrsoftware.ichat.addcontact.viewdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.addcontact.AddContactMVP;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddContactFragment extends DialogFragment implements AddContactMVP.View, DialogInterface.OnShowListener{

    @Nullable
    @BindView(R.id.wrappe_email)
    TextInputLayout wrappe_email;

    @Nullable
    @BindView(R.id.input_email)
    TextInputEditText input_email;

    @Nullable
    @BindView(R.id.addContactProgressbar)
    ProgressBar addContactProgressbar;

    public AddContactFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.add_mesagge_tittle))
                .setPositiveButton(getString(R.string.add_accept), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(getString(R.string.add_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false).show();
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.add_contact, null, false);
        ButterKnife.bind(this,rootView);


        builder.setView(rootView);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
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
        Toast.makeText(getActivity(), getString(R.string.add_success_contactadded),Toast.LENGTH_LONG).show();
    }

    @Override
    public void contactErrorAdded() {
        input_email.setText("");
        input_email.setError(getString(R.string.add_error_contact));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.bind(getActivity()).unbind();
    }

    @Override
    public void onShow(DialogInterface dialog) {
        final AlertDialog alertDialog = (AlertDialog)getDialog();
        if(alertDialog != null){
            Button positiveBtn = alertDialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeBtn = alertDialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            negativeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

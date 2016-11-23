package com.abrsoftware.ichat.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.entities.User;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by AbrWin on 16/11/16.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactHolder> {
    private List<User> values;
    private onItemClickListener listener;

    public ContactListAdapter(List<User> values, onItemClickListener onItemClickListener) {
        this.values = values;
        this.listener = onItemClickListener;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        holder.contact = values.get(position);
        holder.contactEmail.setText(holder.contact.getEmail());
        holder.contactStatus.setText(holder.contact.isOnline()
                ? holder.itemView.getContext().getString(R.string.status_online):
                  holder.itemView.getContext().getString(R.string.status_offline));
        Glide.with(holder.itemView.getContext())
                .load(holder.contact.getUrlImge())
                .into(holder.contactImg);
    }

    @Override
    public int getItemCount() {
        if(values != null && values.size() > 0)
            return values.size();
        else
            return 0;
    }

    public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView contactImg;
        private TextView contactEmail;
        private TextView contactStatus;
        private User contact;

        public ContactHolder(View itemView) {
            super(itemView);
            contactImg = (ImageView)itemView.findViewById(R.id.contactImg);
            contactEmail = (TextView)itemView.findViewById(R.id.contactEmail);
            contactStatus = (TextView)itemView.findViewById(R.id.contactStatus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(this);
        }
    }

    interface onItemClickListener{
        void onClick(ContactHolder contactHolder);
    }

}

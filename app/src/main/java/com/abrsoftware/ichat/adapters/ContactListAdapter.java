package com.abrsoftware.ichat.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AbrWin on 16/11/16.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactHolder> {
    private List<User> values;
    public onItemClickListener listener;

    public ContactListAdapter(List<User> contacts, onItemClickListener onItemClickListener) {
        values = contacts;
        this.listener = onItemClickListener;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        holder.contact = values.get(position);
        boolean isOnline = holder.contact.isOnline();
        Context context = holder.itemView.getContext();
        holder.contactEmail.setText(holder.contact.getEmail());
        holder.contactStatus.setTextColor(isOnline ? ContextCompat.getColor(context,R.color.online_color) : ContextCompat.getColor(context, R.color.offline_color));
        holder.contactStatus.setText(isOnline ? context.getString(R.string.status_online) : context.getString(R.string.status_offline));

        Glide.with(context)
                .load(holder.contact.getUrlImge())
                .placeholder(context.getDrawable(R.drawable.common_google_signin_btn_icon_dark))
                .into(holder.contactImg);
    }

    @Override
    public int getItemCount() {
        if (values != null && values.size() > 0)
            return values.size();
        else
            return 0;
    }

    public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.contactImg)
        public ImageView contactImg;

        @BindView(R.id.contactEmail)
        public TextView contactEmail;

        @BindView(R.id.contactStatus)
        public TextView contactStatus;

        public User contact;

        public ContactHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(this);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onLongClick(this);
            return false;
        }
    }

    public interface onItemClickListener {
        void onClick(ContactHolder contactHolder);
        void onLongClick(ContactHolder contactHolder);
    }

    public void add(User user) {
        if (!values.contains(user)) {
            values.add(user);
            notifyDataSetChanged();
        }
    }

    public void update(User user) {
        if (values.contains(user)) {
            int index = values.indexOf(user);
            values.set(index, user);
            notifyDataSetChanged();
        }
    }

    public void remove(User user) {
        if (values.contains(user)) {
            values.remove(user);
            notifyDataSetChanged();
        }
    }


}

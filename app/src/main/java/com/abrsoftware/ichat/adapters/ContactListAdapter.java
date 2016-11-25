package com.abrsoftware.ichat.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

/**
 * Created by AbrWin on 16/11/16.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactHolder> {
    private List<User> values;
    public onItemClickListener listener;

    public ContactListAdapter(List<User> values, onItemClickListener onItemClickListener) {
        this.values = values;
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
        boolean isOnline = holder.contact.isOnline();
        Context context = holder.itemView.getContext();

        holder.contact = values.get(position);
        holder.contactEmail.setText(holder.contact.getEmail());
        holder.contactStatus.setTextColor(isOnline ? context.getColor(R.color.online_color) : context.getColor(R.color.offline_color));
        holder.contactStatus.setText(isOnline ? context.getString(R.string.status_online) : context.getString(R.string.status_offline));

        Glide.with(context)
                .load(holder.contact.getUrlImge())
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(this);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onLongClick();
            return false;
        }
    }

    public interface onItemClickListener {
        void onClick(ContactHolder contactHolder);
        void onLongClick();
    }

}

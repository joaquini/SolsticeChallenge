package com.solstice.solsticechallenge.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.pojo.Contact;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<Contact> contactsList;

    @Inject
    public ContactsAdapter() {
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_list_item, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        String name = contactsList.get(position).getName();
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return contactsList == null ? 0 : contactsList.size();
    }

    public void setContactsList(List<Contact> contactList) {
        this.contactsList = contactList;
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

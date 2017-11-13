package com.solstice.solsticechallenge.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ItemContactsViewHolder> {

    private static final int VIEW_TYPE_FAVORITE = 0;
    private static final int VIEW_TYPE_NON_FAVORITE = 1;

    private List<Contact> favoriteContactsList;
    private List<Contact> nonFavoriteContactsList;

    @Inject
    public ContactsAdapter() {
    }

    public void setContactsList(List<Contact> favoriteContactsList, List<Contact> nonFavoriteContactsList) {
        this.favoriteContactsList = favoriteContactsList;
        this.nonFavoriteContactsList = nonFavoriteContactsList;
    }

    @Override
    public ItemContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_FAVORITE) {
            View view = inflater.inflate(R.layout.contact_list_favorite_item, parent, false);
            return new FavoriteContactsViewHolder(view);
        }

        if (viewType == VIEW_TYPE_NON_FAVORITE) {
            View view = inflater.inflate(R.layout.contact_list_non_favorite_item, parent, false);
            return new NonFavoriteContactsViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(ItemContactsViewHolder holder, int position) {
        Contact contact;

        if (holder.isFavorite()) {
            contact = favoriteContactsList.get(position);
        } else {
            contact = nonFavoriteContactsList.get(position - favoriteContactsList.size());
        }

        String name = contact.getName();
        holder.name.setText(name);
        Picasso.with(holder.image.getContext())
                .load(contact.getSmallImageURL())
                .placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .into(holder.image);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < favoriteContactsList.size()) {
            return VIEW_TYPE_FAVORITE;
        }

        if (position - favoriteContactsList.size() < nonFavoriteContactsList.size()) {
            return VIEW_TYPE_NON_FAVORITE;
        }

        return -1;
    }

    @Override
    public int getItemCount() {
        if (favoriteContactsList == null && nonFavoriteContactsList == null) {
            return 0;
        }

        if (favoriteContactsList == null) {
            return nonFavoriteContactsList.size();
        }

        if (nonFavoriteContactsList == null) {
            return favoriteContactsList.size();
        }

        return favoriteContactsList.size() + nonFavoriteContactsList.size();
    }

    abstract class ItemContactsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.image)
        ImageView image;

        ItemContactsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        abstract boolean isFavorite();
    }

    class FavoriteContactsViewHolder extends ItemContactsViewHolder {

        FavoriteContactsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        boolean isFavorite() {
            return true;
        }
    }

    class NonFavoriteContactsViewHolder extends ItemContactsViewHolder {

        NonFavoriteContactsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        boolean isFavorite() {
            return false;
        }
    }
}

package com.solstice.solsticechallenge.ui.contactsmain.adapter.sections;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.ui.images.ImageLoader;
import com.solstice.solsticechallenge.data.entities.Contact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public abstract class ContactsAbstractSection extends StatelessSection {

    private ImageLoader imageLoader;

    public interface ItemPressedListener {
        void onItemPressed(String id);
    }

    ContactsAbstractSection(SectionParameters sectionParameters, ImageLoader imageLoader) {
        super(sectionParameters);
        this.imageLoader = imageLoader;
    }

    protected abstract List<Contact> getContactList();

    protected abstract ItemPressedListener getItemPressedListener();

    @Override
    public int getContentItemsTotal() {
        return getContactList() == null ? 0 : getContactList().size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final Contact contact = getContactList().get(position);

        ContactsViewHolder holder = (ContactsViewHolder) viewHolder;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItemPressedListener() != null) {
                    getItemPressedListener().onItemPressed(contact.getId());
                }
            }
        });
        holder.name.setText(contact.getName());
        holder.companyName.setText(contact.getCompanyName());
        holder.star.setVisibility(contact.isFavorite() ? View.VISIBLE : View.GONE);
        imageLoader.load(contact.getSmallImageURL(), holder.image, R.drawable.ic_profile, R.drawable.ic_profile);
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.companyName)
        TextView companyName;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.star)
        ImageView star;

        ContactsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

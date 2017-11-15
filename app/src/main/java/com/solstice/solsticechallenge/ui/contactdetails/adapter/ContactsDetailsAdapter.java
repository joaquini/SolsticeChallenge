package com.solstice.solsticechallenge.ui.contactdetails.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.ui.contactdetails.sections.ContactDetailsSection;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsDetailsAdapter extends RecyclerView.Adapter<ContactsDetailsAdapter.ContactDetailsViewHolder>{

    private List<ContactDetailsSection> sections;

    @Inject
    public ContactsDetailsAdapter() {
    }

    public void setSections(List<ContactDetailsSection> sections) {
        this.sections = sections;
    }

    @Override
    public ContactDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_details_section, parent, false);
        return new ContactDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactDetailsViewHolder holder, int position) {
        ContactDetailsSection section = sections.get(position);
        holder.sectionName.setText(section.getSectionName());
        holder.sectionContent.setText(section.getSectionContent());
        if (section.hasSectionDescription()) {
            holder.sectionDescription.setText(section.getSectionDescription());
        }
    }

    @Override
    public int getItemCount() {
        return sections == null ? 0 : sections.size();
    }

    class ContactDetailsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.sectionName)
        TextView sectionName;
        @BindView(R.id.sectionContent)
        TextView sectionContent;
        @BindView(R.id.sectionDescription)
        TextView sectionDescription;

        ContactDetailsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

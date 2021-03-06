package com.solstice.solsticechallenge.ui.contactsmain;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.SolsticeApplication;
import com.solstice.solsticechallenge.ui.contactdetails.ContactDetailsActivity;
import com.solstice.solsticechallenge.ui.contactsmain.adapter.sections.FavoritesSection;
import com.solstice.solsticechallenge.ui.contactsmain.adapter.sections.NonFavoritesSection;
import com.solstice.solsticechallenge.data.entities.Contact;
import com.solstice.solsticechallenge.ui.contactsmain.mvp.presenter.MainPresenter;
import com.solstice.solsticechallenge.ui.contactsmain.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

import static com.solstice.solsticechallenge.Constants.CONTACT_ID;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MainPresenter presenter;
    @Inject
    SectionedRecyclerViewAdapter adapter;
    @Inject
    FavoritesSection favoritesSection;
    @Inject
    NonFavoritesSection nonFavoritesSection;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((SolsticeApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        favoritesSection.setListener(presenter);
        nonFavoritesSection.setListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                presenter.onDeleteContactsItemPressed();
                return true;
        }
        return false;
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showMessage(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContacts(List<Contact> favoriteContactsList, List<Contact> nonFavoriteContactsList) {
        if (!favoriteContactsList.isEmpty()) {
            adapter.addSection(favoritesSection);
        }
        if (!nonFavoriteContactsList.isEmpty()) {
            adapter.addSection(nonFavoritesSection);
        }
        
        favoritesSection.setContactList(favoriteContactsList);
        nonFavoritesSection.setContactList(nonFavoriteContactsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayDeleteContactsConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.dialog_ask_delete_confirmation);
        builder.setPositiveButton(R.string.dialog_ask_delete_positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.onDeleteContactsConfirmed();
            }
        });
        builder.setNegativeButton(R.string.dialog_ask_delete_negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    @Override
    public void navigateToContactDetails(String id) {
        Intent intent = new Intent(getApplicationContext(), ContactDetailsActivity.class);
        intent.putExtra(CONTACT_ID, id);
        startActivity(intent);
    }

    @Override
    public void removeAllSections() {
        adapter.removeAllSections();
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }
}

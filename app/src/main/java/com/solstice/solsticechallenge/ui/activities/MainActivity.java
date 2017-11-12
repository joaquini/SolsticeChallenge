package com.solstice.solsticechallenge.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.SolsticeApplication;
import com.solstice.solsticechallenge.ui.adapter.ContactsAdapter;
import com.solstice.solsticechallenge.ui.mvp.presenter.MainPresenter;
import com.solstice.solsticechallenge.ui.mvp.view.MainView;
import com.solstice.solsticechallenge.pojo.Contact;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainPresenter presenter;
    @Inject
    ContactsAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((SolsticeApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribeBus();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribeBus();
    }

    @Override
    public void showMessage(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContacts(List<Contact> contactList) {
        adapter.setContactsList(contactList);
        adapter.notifyDataSetChanged();
    }
}

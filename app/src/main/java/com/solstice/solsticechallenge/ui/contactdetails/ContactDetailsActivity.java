package com.solstice.solsticechallenge.ui.contactdetails;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.SolsticeApplication;
import com.solstice.solsticechallenge.ui.contactdetails.adapter.ContactsDetailsAdapter;
import com.solstice.solsticechallenge.ui.contactdetails.mvp.presenter.ContactDetailsPresenter;
import com.solstice.solsticechallenge.ui.contactdetails.mvp.view.ContactDetailsView;
import com.solstice.solsticechallenge.ui.contactdetails.sections.ContactDetailsSection;
import com.solstice.solsticechallenge.ui.images.ImageLoader;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.solstice.solsticechallenge.Constants.CONTACT_ID;

public class ContactDetailsActivity extends AppCompatActivity implements ContactDetailsView {

    @Inject
    ContactDetailsPresenter presenter;
    @Inject
    ImageLoader imageLoader;
    @Inject
    ContactsDetailsAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.companyName)
    TextView companyName;

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ((SolsticeApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_details_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
        presenter.onMenuOptionsReady();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                presenter.onFavoriteIconPressed();
                return true;
            case android.R.id.home:
                presenter.onArrowBackPressed();
        }
        return false;
    }

    @Override
    public String getContactId() {
        return getIntent().hasExtra(CONTACT_ID) ? getIntent().getStringExtra(CONTACT_ID) : null;
    }

    @Override
    public void showName(String name) {
        this.name.setText(name);
    }

    @Override
    public void showCompanyName(String companyName) {
        this.companyName.setText(companyName);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showDetailsSections(List<ContactDetailsSection> sections) {
        adapter.setSections(sections);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProfileImage(String url) {
        imageLoader.load(url, image, R.drawable.ic_profile, R.drawable.ic_profile);
    }

    @Override
    public void setFavoritedIcon(@StringRes int stringId) {
        if (menu != null) {
            MenuItem starItem = menu.getItem(0);
            starItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_favorite));
            starItem.setTitle(stringId);
        }
    }

    @Override
    public void setUnfavoritedIcon(@StringRes int stringId) {
        if (menu != null) {
            MenuItem starItem = menu.getItem(0);
            starItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_unfavorite));
            starItem.setTitle(stringId);
        }
    }

    @Override
    public void showMessage(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }
}

package com.solstice.solsticechallenge.ui.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.SolsticeApplication;
import com.solstice.solsticechallenge.ui.images.ImageLoader;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Address;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Phone;
import com.solstice.solsticechallenge.ui.mvp.presenter.ContactDetailsPresenter;
import com.solstice.solsticechallenge.ui.mvp.view.ContactDetailsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.solstice.solsticechallenge.Constants.CONTACT_ID;

public class ContactDetailsActivity extends AppCompatActivity implements ContactDetailsView {

    @Inject
    ContactDetailsPresenter presenter;
    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.companyName)
    TextView companyName;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.birthDate)
    TextView birthDate;
    @BindView(R.id.email)
    TextView email;

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ((SolsticeApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.activity_details_menu, menu);
        return true;
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
    public void showPhone(Phone phone) {
        this.phone.setText(phone.getHome());
    }

    @Override
    public void showAddress(Address address) {
        this.address.setText(address.getCity());
    }

    @Override
    public void showBirthDate(String birthDate) {
        this.birthDate.setText(birthDate);
    }

    @Override
    public void showEmail(String email) {
        this.email.setText(email);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showProfileImage(String url) {
        imageLoader.load(url, image, R.drawable.ic_profile, R.drawable.ic_profile);
    }

    @Override
    public void setFavoritedIcon() {
        if (menu != null) {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_favorite));
        }
    }

    @Override
    public void setUnfavoritedIcon() {
        if (menu != null) {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_unfavorite));
        }
    }

    @Override
    public void showMessage(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }
}

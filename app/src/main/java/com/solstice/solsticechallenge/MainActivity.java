package com.solstice.solsticechallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.solstice.solsticechallenge.mvp.presenter.MainPresenter;
import com.solstice.solsticechallenge.mvp.view.MainView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((SolsticeApplication)getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.start();
    }

    @Override
    public void showMessage(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }
}

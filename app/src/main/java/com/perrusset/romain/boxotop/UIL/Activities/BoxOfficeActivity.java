package com.perrusset.romain.boxotop.UIL.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;
import com.perrusset.romain.boxotop.UIL.Fragments.BoxOfficeFragment;
import com.perrusset.romain.boxotop.UIL.Presenters.BoxOfficePresenter;

public class BoxOfficeActivity extends BaseActivity {

    //region Properties
    private BoxOfficeContract.Presenter _presenter;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onCreateUserInterface();
    }

    @Override
    protected void onCreateUserInterface() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        BoxOfficeFragment fragment = (BoxOfficeFragment) fragmentManager
                .findFragmentById(R.id.fragment);

        if (fragment == null) {
            fragment = BoxOfficeFragment.newInstance();
            _presenter = new BoxOfficePresenter(this, fragment);
            fragment.setPresenter(_presenter);

            startFragment(R.id.fragment,fragment);
        }
    }

    @Override
    public void onBackPressed() {
        _presenter.onBackPressed();
    }

}

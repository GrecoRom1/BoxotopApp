package com.perrusset.romain.boxotop.UIL.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Fragments.BoxOfficeFragment;
import com.perrusset.romain.boxotop.UIL.Presenter.BoxOfficePresenter;

public class BoxOfficeActivity extends AppCompatActivity {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    private BoxOfficeFragment rootFragment;
    private BoxOfficeFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onCreateUserInterface();
    }


    private void onCreateUserInterface() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        BoxOfficeFragment fragment = (BoxOfficeFragment)fragmentManager
                .findFragmentById(R.id.fragment);

        if (fragment == null)
        {
            fragment = BoxOfficeFragment.newInstance();
            fragment.setPresenter(new BoxOfficePresenter(this,fragment));

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}

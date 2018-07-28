package com.perrusset.romain.boxotop.UIL.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    //region Abstract Methods

    /**
     * Create the View and Presenter of MVP
     * Instantiate the fragment and the Presenter
     */
    abstract void onCreateUserInterface();
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //region Protected Method

    /**
     * Handle the transaction to start the fragment
     * @param containerId the id of the container for the fragment
     * @param fragment the fragment to be commit
     */
    protected void startFragment(int containerId, Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //endregion
}

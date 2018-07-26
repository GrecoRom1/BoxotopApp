package com.perrusset.romain.boxotop.UIL.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Contracts.MovieDetailsContract;
import com.perrusset.romain.boxotop.UIL.Fragments.MovieDetailsFragment;
import com.perrusset.romain.boxotop.UIL.Presenter.MovieDetailsPresenter;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String MOVIE_ID = "movieID";
    private int mMovieID;
    private MovieDetailsContract.Presenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mMovieID = getIntent().getIntExtra(MOVIE_ID,-1);
        onCreateUserInterface();
    }


    private void onCreateUserInterface() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieDetailsFragment fragment = (MovieDetailsFragment) fragmentManager
                .findFragmentById(R.id.fragment);

        if (fragment == null) {
            fragment = MovieDetailsFragment.newInstance(mMovieID);
            _presenter = new MovieDetailsPresenter(this, fragment);
            fragment.setPresenter(_presenter);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

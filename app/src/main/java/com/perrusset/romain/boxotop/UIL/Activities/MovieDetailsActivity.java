package com.perrusset.romain.boxotop.UIL.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Contracts.MovieDetailsContract;
import com.perrusset.romain.boxotop.UIL.Fragments.MovieDetailsFragment;
import com.perrusset.romain.boxotop.UIL.Presenters.MovieDetailsPresenter;

public class MovieDetailsActivity extends BaseActivity {

    //region Static Properties
    private static final String MOVIE_ID = "movieID";

    //endregion

    //region Properties
    private int mMovieID;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_details);
        mMovieID = getIntent().getIntExtra(MOVIE_ID, -1);

        onCreateUserInterface();
    }

    @Override
    protected void onCreateUserInterface() {

        FragmentManager lFragmentManager = getSupportFragmentManager();
        MovieDetailsFragment lFragment = (MovieDetailsFragment) lFragmentManager
                .findFragmentById(R.id.fragment);

        if (lFragment == null) {
            lFragment = MovieDetailsFragment.newInstance(mMovieID);
            MovieDetailsContract.Presenter mPresenter = new MovieDetailsPresenter(this, lFragment);
            lFragment.setPresenter(mPresenter);

            startFragment(R.id.fragment, lFragment);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

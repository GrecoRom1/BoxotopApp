package com.perrusset.romain.boxotop.UIL.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Model.CastingList;
import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.MovieDetailsContract;
import com.perrusset.romain.boxotop.UIL.Model.Movie;
import com.squareup.picasso.Picasso;


public class MovieDetailsFragment extends BaseFragment
        implements MovieDetailsContract.View, View.OnClickListener,
        android.support.design.widget.AppBarLayout.OnOffsetChangedListener {

    //region Static Properties
    private static final String MOVIE_ID = "movieID";
    //endregion

    //region Properties
    private int mMovieId;
    private MovieDetailsContract.Presenter mPresenter;

    private CollapsingToolbarLayout mCollapsingToolbar;
    private android.support.design.widget.AppBarLayout mAppBarLayout;

    private android.support.v7.widget.Toolbar toolbar;

    private boolean appBarExpanded = true;
    private boolean previousStateAppBarExpanded = true;

    private ImageView image;
    private TextView title;
    private TextView director;
    private TextView casting;
    private TextView releaseDate;
    private TextView duration;
    private RatingBar audienceRating;
    private TextView overview;

    //endregion
    public MovieDetailsFragment() {
    }


    public static MovieDetailsFragment newInstance(int movieId) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieId = getArguments().getInt(MOVIE_ID);
        }

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        //Tell that the fragment handle the toolbar menu
        setHasOptionsMenu(true);

        //Toolbar

        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        mAppBarLayout = view.findViewById(R.id.app_bar);
        mCollapsingToolbar = view.findViewById(R.id.toolbar_layout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mAppBarLayout.addOnOffsetChangedListener(this);

        //Get all the reference for the view
        image = view.findViewById(R.id.header);
        director = view.findViewById(R.id.textView_director);
        casting = view.findViewById(R.id.textView_casting);
        releaseDate = view.findViewById(R.id.textView_releaseDate);
        duration = view.findViewById(R.id.textView_duration);
        audienceRating = view.findViewById(R.id.rating_people);
        overview = view.findViewById(R.id.textview_description);

        getActivity().invalidateOptionsMenu();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start(mMovieId);
    }

    @Override
    public void notifyDataMovieLoaded(Movie movie) {

        //Set data to view element
        mCollapsingToolbar.setTitle(movie.getTitle());
        director.setText(R.string.movieDetails_director);
        casting.setText(R.string.movieDetails_casting);

        String releaseDateString = getString(R.string.movieDetails_releaseDate)
                + movie.getReleaseDate();
        releaseDate.setText(releaseDateString);

        String durationString = getString(R.string.movieDetails_duration)
                + movie.getDurationFormattedString();
        duration.setText(durationString);

        audienceRating.setRating(movie.getVoteAverage());
        overview.setText(movie.getSynopsis());

        //Set image
        Picasso.with(getContext()).load(movie.getURLFormattedString()).into(image);

        mPresenter.getCastList(mMovieId);
    }

    @Override
    public void notifyDataCastLoaded(CastingList listCast) {

        String castingString = getString(R.string.movieDetails_casting)
                + listCast.getStringActorList();
        casting.setText(castingString);

        String directorString = getString(R.string.movieDetails_director)
                + listCast.getDirectorString();
        director.setText(directorString);
    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        mPresenter = (MovieDetailsContract.Presenter) presenter;
    }

    @Override
    public void onClick(View view) {
        getActivity().finish();
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        toolbar.invalidate(); // update the toolbar
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_scrolling, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

}

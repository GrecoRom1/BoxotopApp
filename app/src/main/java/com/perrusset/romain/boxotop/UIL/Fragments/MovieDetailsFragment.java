package com.perrusset.romain.boxotop.UIL.Fragments;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
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
import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.MovieDetailsContract;
import com.perrusset.romain.boxotop.UIL.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieDetailsFragment extends Fragment implements MovieDetailsContract.View, View.OnClickListener, android.support.design.widget.AppBarLayout.OnOffsetChangedListener {

    private static final String MOVIE_ID = "movieID";

    private int mMovieId;
    private MovieDetailsContract.Presenter _presenter;

    private CollapsingToolbarLayout collapsingToolbar;
    private android.support.design.widget.AppBarLayout appBarLayout;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        //Tell that the fragment handle the toolbar menu
        setHasOptionsMenu(true);

        //Toolbar

        toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);
        //Toolbar.SetOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        appBarLayout = (android.support.design.widget.AppBarLayout) view.findViewById(R.id.app_bar);
        collapsingToolbar = view.findViewById(R.id.toolbar_layout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        appBarLayout.addOnOffsetChangedListener(this);

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
        _presenter.start();
    }

    @Override
    public void notifiyDataMovieLoaded(Movie movie) {

        //Set data to view element
        collapsingToolbar.setTitle(movie.originalTitle);
        director.setText("Director (incoming!)");
        casting.setText("Casting (incoming!)");
        releaseDate.setText("Release date : " + movie.releaseDate);
        duration.setText(" Duration : "+movie.getDurationFormattedString());
        audienceRating.setRating(movie.getVoteAverage());
        overview.setText(movie.synopsis);

        //Set image
        Picasso.with(getContext()).load(movie.getURLFormattedString()).into(image);

        _presenter.getCastList(mMovieId);
    }

    @Override
    public void notifyPresenterReady() {
        _presenter.getMovieDetails(mMovieId);
    }

    @Override
    public void notifiyDataCastLoaded(ArrayList listCast) {

    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        _presenter = (MovieDetailsContract.Presenter) presenter;
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (Math.abs(i) > 200) {
            appBarExpanded = false;
            toolbar.invalidate();
        } else {
            appBarExpanded = true;
            toolbar.invalidate();
        }

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

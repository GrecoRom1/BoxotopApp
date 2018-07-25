package com.perrusset.romain.boxotop.UIL.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.MovieDetailsContract;
import com.perrusset.romain.boxotop.UIL.Movie;

import java.util.ArrayList;


public class MovieDetailsFragment extends Fragment implements MovieDetailsContract.View {

    private static final String MOVIE_ID = "movieID";

    private int mMovieId;
    private MovieDetailsContract.Presenter _presenter;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _presenter.start();
    }

    @Override
    public void notifiyDataMovieLoaded(Movie movie) {

        //Set data to view element
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

}

package com.perrusset.romain.boxotop.UIL.Presenters;

import android.content.Context;

import com.perrusset.romain.boxotop.UIL.Model.CastingList;
import com.perrusset.romain.boxotop.UIL.Contracts.MovieDetailsContract;
import com.perrusset.romain.boxotop.UIL.Model.Movie;

import SAL.Callback.CallbackMoviesAPI;
import SAL.ErrorMoviesAPIEnum;
import SAL.MoviesAPI;

public class MovieDetailsPresenter extends BasePresenter implements MovieDetailsContract.Presenter, CallbackMoviesAPI.MovieDetails {

    private MovieDetailsContract.View _view;
    private boolean isInitialized;

    private MoviesAPI _moviesAPI;
    private int mMovieID;


    public MovieDetailsPresenter(Context c, MovieDetailsContract.View view) {
        super(c);
        _view = view;
        isInitialized = false;
    }

    @Override
    public void start(){
    }

    @Override
    public void start(int movieId) {
        if (!isInitialized) {
            isInitialized = true;
            _moviesAPI = new MoviesAPI(mContext);
            mMovieID = movieId;
            getMovieDetails(movieId);
        }
    }

    private void getMovieDetails(int movieId) {
        _moviesAPI.getDetailsMovie(movieId, this);
    }

    @Override
    public void getCastList(int movieId) {
        _moviesAPI.getCastListOfMovie(movieId, this);
    }


    @Override
    public void onResultDetailsMovie(Movie movie) {
        _view.notifyDataMovieLoaded(movie);
    }

    @Override
    public void onResultCastListOfMovie(CastingList castList) {
        _view.notifyDataCastLoaded(castList);
    }

    @Override
    public void OnError(ErrorMoviesAPIEnum error) {

    }


}

package com.perrusset.romain.boxotop.UIL.Presenter;

import android.content.Context;

import com.perrusset.romain.boxotop.UIL.Cast;
import com.perrusset.romain.boxotop.UIL.Contracts.MovieDetailsContract;
import com.perrusset.romain.boxotop.UIL.Movie;
import com.perrusset.romain.boxotop.UIL.SAL.CallbackMoviesAPI;
import com.perrusset.romain.boxotop.UIL.SAL.ErrorMoviesAPIEnum;
import com.perrusset.romain.boxotop.UIL.SAL.MoviesAPI;

import java.util.ArrayList;

public class MovieDetailsPresenter extends BasePresenter implements MovieDetailsContract.Presenter, CallbackMoviesAPI.MovieDetails {

    private MovieDetailsContract.View _view;
    private boolean isInitialized;

    private MoviesAPI _moviesAPI;


    public MovieDetailsPresenter(Context c, MovieDetailsContract.View view) {
        super(c);
        _view = view;
        isInitialized = false;
    }

    @Override
    public void start() {
        if (!isInitialized) {
            isInitialized = true;
            _moviesAPI = new MoviesAPI(mContext);
            _view.notifyPresenterReady();
        }
    }

    @Override
    public void getMovieDetails(int movieId) {
        _moviesAPI.getDetailsMovie(movieId, this);
    }

    @Override
    public void getCastList(int movieId) {
        _moviesAPI.getCastListOfMovie(movieId, this);
    }


    @Override
    public void onResultDetailsMovie(Movie movie) {
        _view.notifiyDataMovieLoaded(movie);
    }

    @Override
    public void onResultCastListOfMovie(ArrayList<Cast> castList) {
        _view.notifiyDataCastLoaded(castList);
    }

    @Override
    public void OnError(ErrorMoviesAPIEnum error) {

    }


}

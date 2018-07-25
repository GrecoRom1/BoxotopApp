package com.perrusset.romain.boxotop.UIL.SAL;

import com.perrusset.romain.boxotop.UIL.Cast;
import com.perrusset.romain.boxotop.UIL.Movie;

import java.util.ArrayList;

public interface CallbackMoviesAPI {
    void onResultBoxOffice(ArrayList<Movie> result, int page);
    void onResultSearch(ArrayList<Movie> result, int page);
    void OnError(ErrorMoviesAPIEnum error);
    void onResultDetailsMovie(Movie movie);
    void onResultCastListOfMovie(ArrayList<Cast> castList);

}

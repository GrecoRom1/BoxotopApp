package com.perrusset.romain.boxotop.UIL.SAL;

import com.perrusset.romain.boxotop.UIL.Movie;

import java.util.ArrayList;

public interface CallbackMoviesAPI {
    void OnResultBoxOffice(ArrayList<Movie> result,int page);
    void OnResultSearch(ArrayList<Movie> result, int page);
    void OnError(ErrorMoviesAPIEnum error);
    //void OnResultMovieDetail(Movie result);

}

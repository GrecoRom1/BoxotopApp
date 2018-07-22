package com.perrusset.romain.boxotop.UIL.SAL;

import com.perrusset.romain.boxotop.UIL.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    static String apiKey = "?api_key=51e802d86984a4cef0692e2e7ad2df7e";

    @GET("/3/movie/popular"+apiKey)
    Call<MovieList> getPopularMovie(@Query("page") int pageNumber);
}

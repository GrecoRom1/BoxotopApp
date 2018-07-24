package com.perrusset.romain.boxotop.UIL.SAL;

import android.content.Context;
import android.util.Log;

import com.perrusset.romain.boxotop.UIL.MovieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesAPI {

    private Context mContext;

    private boolean IsWaitEnough = false;

    public MoviesAPI(Context context) {
        mContext = context;
    }

    public void getPopularMovie(final int page, final CallbackMoviesAPI callback) {
        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance(mContext).create(GetDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<MovieList> call = service.getPopularMovie(page);

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                callback.OnResultBoxOffice(response.body().getMovieArrayList(), page);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                //todo callback.OnError(ErrorMoviesAPIEnum.SearchEmpty);
            }
        });
    }


    public void getMovieFromSearch(String query, final int page, final CallbackMoviesAPI callback) {
        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance(mContext).create(GetDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<MovieList> call = service.getMovieFromSearch(query, page);

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                callback.OnResultSearch(response.body().getMovieArrayList(), page);


            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}

package SAL;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.perrusset.romain.boxotop.UIL.Model.CastingList;
import com.perrusset.romain.boxotop.UIL.Model.Movie;
import com.perrusset.romain.boxotop.UIL.Model.MovieList;

import SAL.Callback.CallbackMoviesAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesAPI {

    private Context mContext;

    public MoviesAPI(Context context) {
        mContext = context;
    }

    //region Box Office
    public void getPopularMovie(final int page, final CallbackMoviesAPI.BoxOffice callback) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance(mContext).create(GetDataService.class);

        // Call the method with parameter in the interface to get the notice data
        Call<MovieList> call = service.getPopularMovie(page);

        //Log the URL called
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(@NonNull Call<MovieList> call, @NonNull Response<MovieList> response) {
                callback.onResultBoxOffice(response.body().getMovieArrayList(), page);
            }

            @Override
            public void onFailure(@NonNull Call<MovieList> call, @NonNull Throwable t) {
                //todo callback.OnError(ErrorMoviesAPIEnum.SearchEmpty);
            }
        });
    }


    public void getMovieFromSearch(String query, final int page, final CallbackMoviesAPI.BoxOffice callback) {
        //Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance(mContext).create(GetDataService.class);

        //Call the method with parameter in the interface to get the notice data
        if (query != null) {

            Call<MovieList> call = service.getMovieFromSearch(query, page);

            //Log the URL called
            Log.wtf("URL Called", call.request().url() + "");

            call.enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(@NonNull Call<MovieList> call, @NonNull Response<MovieList> response) {
                    callback.onResultSearch(response.body().getMovieArrayList(), page);
                }

                @Override
                public void onFailure(@NonNull Call<MovieList> call, @NonNull Throwable t) {
                    //todo callback.OnError(ErrorMoviesAPIEnum.SearchEmpty);
                }
            });
        }
    }
    //endregion

    //region MovieDetails
    public void getDetailsMovie(int movieId, final CallbackMoviesAPI.MovieDetails callback) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance(mContext).create(GetDataService.class);

        // Call the method with parameter in the interface to get the notice data
        Call<Movie> call = service.getDetailMovie(movieId);

        //Log the URL called
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                callback.onResultDetailsMovie(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                //todo callback.OnError(ErrorMoviesAPIEnum.SearchEmpty);
            }
        });
    }

    public void getCastListOfMovie(int movieId, final CallbackMoviesAPI.MovieDetails callback) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance(mContext).create(GetDataService.class);

        // Call the method with parameter in the interface to get the notice data
        Call<CastingList> call = service.getCastingList(movieId);

        //Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<CastingList>() {
            @Override
            public void onResponse(@NonNull Call<CastingList> call, @NonNull Response<CastingList> response) {
                callback.onResultCastListOfMovie(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CastingList> call, @NonNull Throwable t) {
                //todo callback.OnError(ErrorMoviesAPIEnum.SearchEmpty);
            }
        });
    }
    //endregion
}

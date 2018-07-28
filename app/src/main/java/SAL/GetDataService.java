package SAL;

import com.perrusset.romain.boxotop.UIL.Model.CastingList;
import com.perrusset.romain.boxotop.UIL.Model.Movie;
import com.perrusset.romain.boxotop.UIL.Model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    String apiKey = "?api_key=51e802d86984a4cef0692e2e7ad2df7e";

    @GET("/3/movie/now_playing"+apiKey)
    Call<MovieList> getPopularMovie(@Query("page") int pageNumber);

    @GET("/3/search/movie"+apiKey)
    Call<MovieList> getMovieFromSearch(@Query("query") String search, @Query("page")
            int pageNumber);

    @GET("/3/movie/{movie_id}"+apiKey)
    Call<Movie> getDetailMovie(@Path("movie_id") int idMovie);

    @GET("/3/movie/{movie_id}/credits"+apiKey)
    Call<CastingList> getCastingList(@Path("movie_id") int idMovie);
}

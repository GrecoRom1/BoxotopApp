package SAL.Callback;

import com.perrusset.romain.boxotop.UIL.Model.CastingList;
import com.perrusset.romain.boxotop.UIL.Model.Movie;

import java.util.ArrayList;

import SAL.ErrorMoviesAPIEnum;

public interface CallbackMoviesAPI {
    void OnError(ErrorMoviesAPIEnum error);

    interface BoxOffice extends CallbackMoviesAPI{
        void onResultBoxOffice(ArrayList<Movie> result, int page);
        void onResultSearch(ArrayList<Movie> result, int page);
    }

    interface MovieDetails extends CallbackMoviesAPI{
        void onResultDetailsMovie(Movie movie);
        void onResultCastListOfMovie(CastingList castList);
    }

}



package com.perrusset.romain.boxotop.UIL.Contracts;

import com.perrusset.romain.boxotop.UIL.Cast;
import com.perrusset.romain.boxotop.UIL.Movie;

import java.util.ArrayList;

public interface MovieDetailsContract extends BaseContract {
    interface View<T extends Presenter> extends BaseContract.View {
        void notifiyDataMovieLoaded(Movie movie);
        void notifiyDataCastLoaded(ArrayList<Cast> listCast);
        void notifyPresenterReady();
    }
    interface Presenter extends BaseContract.Presenter {
        void getMovieDetails(int movieId);
        void getCastList(int movieId);

    }

}

package com.perrusset.romain.boxotop.UIL.Contracts;

import com.perrusset.romain.boxotop.UIL.Model.CastingList;
import com.perrusset.romain.boxotop.UIL.Model.Movie;

public interface MovieDetailsContract extends BaseContract {
    interface View<T extends Presenter> extends BaseContract.View {
        void notifyDataMovieLoaded(Movie movie);
        void notifyDataCastLoaded(CastingList listCast);
    }
    interface Presenter extends BaseContract.Presenter {
        void start(int movieId);
        void getCastList(int movieId);
    }

}

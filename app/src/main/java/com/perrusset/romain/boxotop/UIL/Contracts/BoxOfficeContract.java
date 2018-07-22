package com.perrusset.romain.boxotop.UIL.Contracts;

import com.perrusset.romain.boxotop.UIL.Movie;

import java.util.ArrayList;

public interface BoxOfficeContract extends BaseContract {

    interface View<T extends Presenter> extends BaseContract.View {
        void notifyPresenterReady(ArrayList<Movie> movies);
        void notifyDataLoaded();
        void startNextActivity(int movieID);
    }

    interface Presenter<I extends View> extends BaseContract.Presenter {
        void loadMoreData();
        void movieCardClicked(int movieID);
    }
}

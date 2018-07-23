package com.perrusset.romain.boxotop.UIL.Contracts;

import com.perrusset.romain.boxotop.UIL.Movie;

import java.util.ArrayList;

public interface BoxOfficeContract extends BaseContract {

    interface View<T extends Presenter> extends BaseContract.View {
        void notifyPresenterReady(ArrayList<Movie> movies);
        void notifyDataLoaded();
        void notifyDataRefreshed();
        void addList(ArrayList<Movie> movies);
        void clearList();
        void setSearchEmpty();
        void startNextActivity(int movieID);
        void setListFull();
        void setListNotFull();
    }

    interface Presenter extends BaseContract.Presenter {
        void onRefresh();
        void onLoadMoreData();
        void movieCardClicked(int movieID);
        void onSearch(String query);
        void onSearchClosed();
    }
}

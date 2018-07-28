package com.perrusset.romain.boxotop.UIL.Contracts;

import com.perrusset.romain.boxotop.UIL.Model.Movie;

import java.util.ArrayList;

public interface BoxOfficeContract extends BaseContract {

    interface View<T extends Presenter> extends BaseContract.View {
        void addList(ArrayList<Movie> movies);
        void clearList();
        void startNextActivity(int movieID);
        void setListFull();
        void setListNotFull();
        void terminateActivity();
    }

    interface Presenter extends BaseContract.Presenter {
        void onRefresh();
        void onLoadMoreData();
        void movieCardClicked(int position);
        void onSearchOpen();
        void onSearch(String query);
        void onQuickSearch(String query);
        void onSearchEmpty();
        void onSearchClosed();
        void onBackPressed();
    }
}

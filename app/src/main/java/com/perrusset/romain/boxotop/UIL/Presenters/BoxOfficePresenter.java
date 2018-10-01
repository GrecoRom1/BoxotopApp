package com.perrusset.romain.boxotop.UIL.Presenters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;
import com.perrusset.romain.boxotop.UIL.Model.Movie;

import java.util.ArrayList;

import SAL.Callback.CallbackMoviesAPI;
import SAL.ErrorMoviesAPIEnum;
import SAL.MoviesAPI;

public class BoxOfficePresenter extends BasePresenter implements BoxOfficeContract.Presenter,
        CallbackMoviesAPI.BoxOffice {

    //region Properties
    private BoxOfficeContract.View mView;
    private ArrayList<Movie> mBoxOfficeList;
    private ArrayList<Movie> mSearchResultList;

    private String mQuickStringSearch = "";
    private boolean mIsAlreadyWaiting = false;

    private boolean mIsLoading;

    private MoviesAPI _moviesAPI;

    private boolean mIsOnSearch;
    private boolean mIsInitialized;

    private String mSearchValue;

    private int pageBoxOffice;
    private int mPageSearch;
    //endregion

    public BoxOfficePresenter(Context c, BoxOfficeContract.View view) {
        super(c);
        mView = view;
        mIsOnSearch = false;
        mIsInitialized = false;
    }

    //region Contract Implementation
    @Override
    public void start() {
        if (!mIsInitialized) {
            mIsInitialized = true;
            mBoxOfficeList = new ArrayList<>();
            mSearchResultList = new ArrayList<>();
            _moviesAPI = new MoviesAPI(mContext);
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        pageBoxOffice = 1;
        onLoadMoreData();
        // future implementation
    }

    @Override
    public void onLoadMoreData() {
        if (!mIsLoading) {
            mIsLoading = true;
            if (mIsOnSearch) {
                loadMoreSearch();
            } else {
                loadMoreBoxOffice();
            }
        }
    }

    private void loadMoreBoxOffice() {
        _moviesAPI.getPopularMovie(pageBoxOffice, this);
    }

    private void loadMoreSearch() {
        _moviesAPI.getMovieFromSearch(mSearchValue, mPageSearch, this);
    }


    @Override
    public void movieCardClicked(int position) {
        int movieId;
        if (mIsOnSearch) {
            movieId = mSearchResultList.get(position).getId();
        } else {
            movieId = mBoxOfficeList.get(position).getId();
        }
        mView.startNextActivity(movieId);
    }


    @Override
    public void onResultBoxOffice(ArrayList<Movie> result, int page) {
        if (result.size() < 20) {
            mView.setListFull();
        } else {
            mView.addList(result);
            mView.setListNotFull();
            mBoxOfficeList.addAll(result);
        }
        pageBoxOffice++;
        mIsLoading = false;
    }

    @Override
    public void onResultSearch(ArrayList<Movie> result, int page) {
        if (result.size() < 20) {
            mView.setListFull();
        } else {
            mView.addList(result);
            mView.setListNotFull();
            mSearchResultList.addAll(result);
        }
        mPageSearch++;
        mIsLoading = false;
    }

    @Override
    public void OnError(ErrorMoviesAPIEnum error) {
    //future implementation
    }

    @Override
    public void onBackPressed() {
        if (mIsOnSearch) {
            onSearchClosed();
        } else {
            mView.terminateActivity();
        }
    }

    @Override
    public void onSearch(String query) {

        if (query.equals("")) {
            onSearchEmpty();
        } else {
            mIsAlreadyWaiting = false;
            if ((!query.equals(mSearchValue))) {
                mPageSearch = 1;
                mIsLoading = true;
                mView.clearList();
                mSearchResultList.clear();
                mSearchValue = query;
                _moviesAPI.getMovieFromSearch(mSearchValue, mPageSearch, this);
            }
        }
    }

    @Override
    public void onQuickSearch(String query) {
        mQuickStringSearch = query;

        if (!mIsAlreadyWaiting) {
            mIsAlreadyWaiting = true;
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onSearch(mQuickStringSearch);
                }
            }, 500);
        }
    }

    @Override
    public void onSearchOpen() {
        mView.clearList();
        mIsOnSearch = true;
        mPageSearch = 1;
        mIsLoading = false;
    }

    @Override
    public void onSearchEmpty() {
        mPageSearch = 1;
        mSearchResultList.clear();
        mView.clearList();
        mIsLoading = false;
    }

    @Override
    public void onSearchClosed() {
        mIsOnSearch = false;
        mIsLoading = false;
        mSearchResultList.clear();
        mView.clearList();
        mView.addList(mBoxOfficeList);
    }
}

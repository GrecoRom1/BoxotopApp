package com.perrusset.romain.boxotop.UIL.Presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;
import com.perrusset.romain.boxotop.UIL.Movie;
import com.perrusset.romain.boxotop.UIL.SAL.CallbackMoviesAPI;
import com.perrusset.romain.boxotop.UIL.SAL.ErrorMoviesAPIEnum;
import com.perrusset.romain.boxotop.UIL.SAL.MoviesAPI;

import java.util.ArrayList;

public class BoxOfficePresenter extends BasePresenter implements BoxOfficeContract.Presenter, CallbackMoviesAPI.BoxOffice {

    private BoxOfficeContract.View _view;
    private ArrayList<Movie> boxOfficeList;
    private ArrayList<Movie> searchResultList;
    private int positionInBoxOfficeList;

    private String quickStringSearch = "";
    private boolean isAlreadyWaiting = false;

    private boolean isLoading;

    private MoviesAPI _moviesAPI;

    private boolean isOnSearch;
    private boolean isInitialized;

    private String searchValue;

    private int pageBoxOffice;
    private int pageSearch;

    public BoxOfficePresenter(Context c, BoxOfficeContract.View view) {
        super(c);
        _view = view;
        isOnSearch = false;
        isInitialized = false;
    }

    @Override
    public void start() {
        if (!isInitialized) {
            isInitialized = true;
            boxOfficeList = new ArrayList<Movie>();
            searchResultList = new ArrayList<Movie>();
            positionInBoxOfficeList = 0;
            _moviesAPI = new MoviesAPI(mContext);
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        pageBoxOffice = 1;
        // _moviesAPI.getPopularMovie(pageBoxOffice, this);
    }

    @Override
    public void onLoadMoreData() {

        if (!isLoading) {
            isLoading = true;
            if (isOnSearch) {
                loadMoreSearch();
            } else {
                loadMoreBoxOffice();
            }
        }
    }

    @Override
    public void movieCardClicked(int position) {
        int movieId;
        if (isOnSearch) {
            movieId = searchResultList.get(position).id;
        } else {
            movieId = boxOfficeList.get(position).id;
        }
        _view.startNextActivity(movieId);
    }


    private void loadMoreBoxOffice() {
        _moviesAPI.getPopularMovie(pageBoxOffice, this);
    }

    private void loadMoreSearch() {
        _moviesAPI.getMovieFromSearch(searchValue, pageSearch, this);
    }


    @Override
    public void onResultBoxOffice(ArrayList<Movie> result, int page) {
        if (result.size() < 20) {
            _view.setListFull();
        } else {
            _view.addList(result);
            _view.setListNotFull();
            boxOfficeList.addAll(result);
        }
        pageBoxOffice++;
        isLoading = false;
    }

    @Override
    public void onResultSearch(ArrayList<Movie> result, int page) {
        if (result.size() < 20) {
            _view.setListFull();
        } else {
            _view.addList(result);
            _view.setListNotFull();
            searchResultList.addAll(result);
        }
        pageSearch++;
        isLoading = false;
    }

    @Override
    public void OnError(ErrorMoviesAPIEnum error) {

    }

    @Override
    public void onBackPressed() {
        if (isOnSearch) {
            onSearchClosed();
        } else {
            _view.terminateActivity();
        }
    }

    @Override
    public void onSearch(String query) {

        if (query.equals("")) {
            onSearchEmpty();
        } else {
            isAlreadyWaiting = false;
            if ((!query.equals(searchValue))) {
                pageSearch = 1;
                isLoading = true;
                _view.clearList();
                searchResultList.clear();
                searchValue = query;
                _moviesAPI.getMovieFromSearch(searchValue, pageSearch, this);
            }
        }
    }

    @Override
    public void onQuickSearch(String query) {
        quickStringSearch = query;

        if (!isAlreadyWaiting) {
            isAlreadyWaiting = true;
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onSearch(quickStringSearch);
                }
            }, 500);
        }

    }

    @Override
    public void onSearchOpen() {
        _view.clearList();
        isOnSearch = true;
        pageSearch = 1;
        isLoading = false;
    }

    @Override
    public void onSearchEmpty() {
        pageSearch = 1;
        searchResultList.clear();
        _view.clearList();
        isLoading = false;
    }

    @Override
    public void onSearchClosed() {
        isOnSearch = false;
        isLoading = false;
        searchResultList.clear();
        _view.clearList();
        _view.addList(boxOfficeList);
    }
}

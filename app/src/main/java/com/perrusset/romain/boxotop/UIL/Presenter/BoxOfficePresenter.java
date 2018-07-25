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

public class BoxOfficePresenter extends BasePresenter implements BoxOfficeContract.Presenter, CallbackMoviesAPI {

    private BoxOfficeContract.View _view;
    private ArrayList<Movie> boxOfficeList;
    private int positionInBoxOfficeList;

    private String quickStringSearch = "";
    private boolean isAlreadyWaiting = false;

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
            positionInBoxOfficeList = 0;
            _moviesAPI = new MoviesAPI(mContext);
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        pageBoxOffice = 1;
        _moviesAPI.getPopularMovie(pageBoxOffice, this);
    }

    @Override
    public void onLoadMoreData() {

        if (isOnSearch) {
            loadMoreSearch();
        } else {
            loadMoreBoxOffice();
        }
    }


    @Override
    public void movieCardClicked(int movieID) {

    }

    @Override
    public void onSearch(String query) {
        if (query.equals("")) {
            onSearchEmpty();
        } else {
            isAlreadyWaiting = false;
            if ((!query.equals(searchValue))) {
                pageSearch = 1;
                searchValue = query;
                isOnSearch = true;
                _view.clearList();

                _moviesAPI.getMovieFromSearch(searchValue, pageSearch, this);
            }
        }
    }

    @Override
    public void onQuickSearch(String query) {

        if (query.equals("")) {
            onSearchEmpty();
        } else {
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
    }

    private void loadMoreBoxOffice() {
        pageBoxOffice++;
        _moviesAPI.getPopularMovie(pageBoxOffice, this);
    }

    private void loadMoreSearch() {
        pageSearch++;
        _moviesAPI.getMovieFromSearch(searchValue, pageSearch, this);
    }

    @Override
    public void onSearchClosed() {
        isOnSearch = false;
        pageSearch = 0;
        _view.clearList();
        _view.addList(boxOfficeList);
    }

    @Override
    public void onSearchOpen() {
        _view.clearList();
    }

    @Override
    public void onSearchEmpty() {
        _view.clearList();
    }

    @Override
    public void onResultBoxOffice(ArrayList<Movie> result, int page) {

        if (result.size() == 0) {
            _view.setListFull();
        } else {
            if (page < 2) {
                _view.notifyDataRefreshed();
            }
            _view.addList(result);
            _view.setListNotFull();
            boxOfficeList.addAll(result);
        }
    }

    @Override
    public void onResultSearch(ArrayList<Movie> result, int page) {
        if (result.size() == 0) {
            _view.setListFull();
        } else {
            _view.addList(result);
            _view.setListNotFull();
        }
    }

    @Override
    public void OnError(ErrorMoviesAPIEnum error) {

    }
    @Override
    public void onBackPressed(){
        if(isOnSearch){
            onSearchClosed();
        }
        else{
            _view.terminateActivity();
        }
    }
}

package com.perrusset.romain.boxotop.UIL.Presenter;

import android.content.Context;

import com.perrusset.romain.boxotop.UIL.BLL.MoviesBLL;
import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;
import com.perrusset.romain.boxotop.UIL.Movie;

import java.util.ArrayList;

public class BoxOfficePresenter extends BasePresenter implements BoxOfficeContract.Presenter {

    private BoxOfficeContract.View _view;

    private  ArrayList<Movie> moviesList;

    int page;

    public BoxOfficePresenter(Context c) {
        super(c);
    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void movieCardClicked(int movieID) {

    }

    @Override
    public void start(BaseContract.View View) {
        _view = (BoxOfficeContract.View) View;
        initPresenter();
    }


    private void initPresenter() {
        moviesList = new ArrayList<Movie>();
        page=0;

        //Todo load the data
        MoviesBLL.GetListMovie(page);

        _view.notifiyPresenterReady();
    }


}

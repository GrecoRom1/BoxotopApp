package com.perrusset.romain.boxotop.UIL.Presenter;

import android.content.Context;
import android.util.Log;

import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;
import com.perrusset.romain.boxotop.UIL.Movie;
import com.perrusset.romain.boxotop.UIL.MovieList;
import com.perrusset.romain.boxotop.UIL.SAL.GetDataService;
import com.perrusset.romain.boxotop.UIL.SAL.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        GetPopularMovie(1);


    }

    public void GetPopularMovie(int page)
    {
        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<MovieList> call = service.getPopularMovie(page);

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                generateNoticeList(response.body().getNoticeArrayList());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateNoticeList(ArrayList<Movie> noticeArrayList) {

        ArrayList<Movie> a = new ArrayList<Movie>();
        a = noticeArrayList;

        _view.notifiyPresenterReady();
    }


}

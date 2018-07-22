package com.perrusset.romain.boxotop.UIL.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Fragments.BoxOfficeFragment;
import com.perrusset.romain.boxotop.UIL.Movie;
import com.perrusset.romain.boxotop.UIL.MovieList;
import com.perrusset.romain.boxotop.UIL.Presenter.BoxOfficePresenter;
import com.perrusset.romain.boxotop.UIL.SAL.GetDataService;
import com.perrusset.romain.boxotop.UIL.SAL.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoxOfficeActivity extends AppCompatActivity {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    private BoxOfficeFragment rootFragment;
    private BoxOfficeFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startView();
    }

    private void startView() {

        rootFragment = new BoxOfficeFragment();

        //Register the presenter
        BoxOfficePresenter presenter = new BoxOfficePresenter(this);
        rootFragment.setPresenter(presenter);
        //Start the fragment
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, rootFragment);
        transaction.addToBackStack(BACK_STACK_ROOT_TAG);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_box_office, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                performSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //get all text changes
                return false;
            }
        });

        return true;
    }

    private void performSearch(String s) {
        getMovieFromSearch(s);
    }

    public void getMovieFromSearch(String query)
    {
        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<MovieList> call = service.getMovieFromSearch(query,1);

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                setListResult(response.body().getMovieArrayList());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void setListResult(ArrayList<Movie> movieArrayList) {

        LaunchSearchFragment(movieArrayList);
    }

    private void LaunchSearchFragment(ArrayList<Movie> movieArrayList){

        BoxOfficeFragment fragment = (BoxOfficeFragment)getSupportFragmentManager().findFragmentByTag(BACK_STACK_ROOT_TAG);
        if (fragment != null && fragment.isVisible()) {
            //Create search fragment
            searchFragment = new BoxOfficeFragment();
            searchFragment.setPresenter(new BoxOfficePresenter(this,movieArrayList));
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.hide(rootFragment);
            t.add(R.id.fragment, searchFragment);
            t.addToBackStack(null);
        }
        else{
            searchFragment = new BoxOfficeFragment();
            searchFragment.setPresenter(new BoxOfficePresenter(this,movieArrayList));
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.replace(R.id.fragment,searchFragment);
            t.addToBackStack(null);
            t.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            // Do work using string
        }
    }
}

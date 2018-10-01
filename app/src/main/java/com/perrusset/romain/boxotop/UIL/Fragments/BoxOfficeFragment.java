package com.perrusset.romain.boxotop.UIL.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Activities.MovieDetailsActivity;
import com.perrusset.romain.boxotop.UIL.Adapter.MovieCardAdapter;
import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;
import com.perrusset.romain.boxotop.UIL.Listeners.LoadMoreEventListener;
import com.perrusset.romain.boxotop.UIL.Listeners.MovieCardClickListener;
import com.perrusset.romain.boxotop.UIL.Listeners.OnLoadListener;

import java.util.ArrayList;


public class BoxOfficeFragment extends BaseFragment
        implements BoxOfficeContract.View, MenuItem.OnActionExpandListener,
        LoadMoreEventListener, MovieCardClickListener {

    //region Static Properties
    private static final String MOVIE_ID = "movieID";
    //endregion

    //region Properties
    private BoxOfficeContract.Presenter mPresenter;
    private MovieCardAdapter mAdapter;
    //endregion

    public BoxOfficeFragment() {
    }

    public static BoxOfficeFragment newInstance() {
        BoxOfficeFragment fragment = new BoxOfficeFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActivity().invalidateOptionsMenu();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Tell that the fragment handle the toolbar menu
        setHasOptionsMenu(true);

        //Inflate the view
        View v = inflater.inflate(R.layout.fragment_box_office, container, false);

        //Get the recyclerView and set up it
        RecyclerView mRecyclerView = v.findViewById((R.id.recyclerview_box_office));
        mRecyclerView.setHasFixedSize(true);

        //set up the adapter
        mAdapter = new MovieCardAdapter(getContext(), this);

        //Plug the adapter to recyclerView
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // set up the scroll listener to know when to load more data
        OnLoadListener listener = new OnLoadListener(mLayoutManager, this);

        mRecyclerView.addOnScrollListener(listener);

        mAdapter.setmIsListFull(false);


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start();
    }

    //region Menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_box_office, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        // Get the item of the searchbar in the toolbar
        MenuItem item = menu.findItem(R.id.action_search);

        item.setVisible(true);

        // Get the searchbar View
        SearchView _searchview = (SearchView) item.getActionView();

        // Parameter for the searchbar
        _searchview.setIconifiedByDefault(true);
        _searchview.setQueryRefinementEnabled(true);

        // Action when the query is submitted
        _searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                performSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("")) {
                    onSearchBarFieldEmpty();
                } else {
                    performQuickSearch(s);
                }
                return false;
            }
        });

        //Set the event on the searchbar when it is collapsed
        // or expanded
        item.setOnActionExpandListener(this);

        super.onPrepareOptionsMenu(menu);
    }
    //endregion

    //region Contract Implementation
    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        mPresenter = (BoxOfficeContract.Presenter) presenter;
    }

    @Override
    public void clearList() {
        mAdapter.clearList();
    }


    @Override
    public void addList(ArrayList movies) {
        mAdapter.addAll(movies);
    }

    @Override
    public void setListFull() {
        mAdapter.setmIsListFull(true);
        mAdapter.removeLoadingFooter();
    }

    @Override
    public void setListNotFull() {
        mAdapter.setmIsListFull(false);
    }

    @Override
    public void onLoadMoreData() {

        if (!mAdapter.getIsListFull()) {
            mPresenter.onLoadMoreData();
        }
    }

    @Override
    public void startNextActivity(int movieID) {
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra(MOVIE_ID, movieID);
        startActivity(intent);
    }

    @Override
    public void terminateActivity() {
        getActivity().finish();
    }
    //endregion

    //region Callback Listener
    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        mPresenter.onSearchClosed();
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        mPresenter.onSearchOpen();
        return true;
    }

    @Override
    public void movieCardClicked(int position) {
        mPresenter.movieCardClicked(position);
    }
    //endregion

    //region Private Methods
    private void performSearch(String s) {

       String query =  s.trim();
        if (!query.isEmpty()) {
            mAdapter.addLoadingFooter();
            mPresenter.onSearch(query);
        }
    }

    private void performQuickSearch(String s) {
        String query =  s.trim();
        if (!query.isEmpty()) {
            mAdapter.addLoadingFooter();
            mPresenter.onQuickSearch(query);
        }
    }

    private void onSearchBarFieldEmpty() {
        mPresenter.onSearchEmpty();
    }

    //endregion

}

package com.perrusset.romain.boxotop.UIL.Fragments;

import android.os.Bundle;
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
import com.perrusset.romain.boxotop.UIL.Adapter.LoadMoreEventListener;
import com.perrusset.romain.boxotop.UIL.Adapter.MovieCardAdapter;
import com.perrusset.romain.boxotop.UIL.Adapter.MovieCardClickListener;
import com.perrusset.romain.boxotop.UIL.Adapter.OnLoadListener;
import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoxOfficeFragment extends android.support.v4.app.Fragment
        implements BoxOfficeContract.View, MenuItem.OnActionExpandListener, LoadMoreEventListener, MovieCardClickListener {

    private BoxOfficeContract.Presenter _presenter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private boolean IsSearchBarOpen;


    private MovieCardAdapter mAdapter;

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

        //Retain the instance of fragment
        setRetainInstance(true);


        this.getActivity().invalidateOptionsMenu();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Tell that the fragment handle the toolbar menu
        setHasOptionsMenu(true);

        //Inflate the view
        View v = inflater.inflate(R.layout.fragment_box_office, container, false);

        //Get the recyclerView and set up it
        mRecyclerView = v.findViewById((R.id.recyclerview_box_office));
        mRecyclerView.setHasFixedSize(true);

        //set up the adapter
        mAdapter = new MovieCardAdapter(getContext(), this);

        //Plug the adapter to recyclerView
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // set up the scroll listener to know when to load more data
        OnLoadListener listener = new OnLoadListener(mLayoutManager, this);

        mRecyclerView.addOnScrollListener(listener);

        mAdapter.setListFull(false);

        mAdapter.addLoadingFooter();

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _presenter.start();
    }


    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        _presenter = (BoxOfficeContract.Presenter) presenter;
    }


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

    @Override
    public void notifyDataLoaded() {

    }

    @Override
    public void notifyDataRefreshed() {

    }

    @Override
    public void clearList() {
        mAdapter.clearList();
    }

    @Override
    public void setSearchEmpty() {

    }

    @Override
    public void addList(ArrayList arrayList) {
        mAdapter.addAll(arrayList);
    }

    @Override
    public void setListFull() {
        mAdapter.setListFull(true);
        mAdapter.removeLoadingFooter();
    }

    @Override
    public void setListNotFull() {
        mAdapter.setListFull(false);
    }

    private void performSearch(String s) {

        if (!s.equals("")) {
            _presenter.onSearch(s);
        }
    }

    private void performQuickSearch(String s) {
        _presenter.onQuickSearch(s);
    }

    private void onSearchBarFieldEmpty() {
        _presenter.onSearchEmpty();
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        _presenter.onSearchClosed();
        return true;
    }


    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        _presenter.onSearchOpen();
        return true;
    }


    @Override
    public void onLoadMoreData() {

        if (!mAdapter.getIsListFull()) {
            _presenter.onLoadMoreData();
        }
    }

    @Override
    public void startNextActivity(int movieID) {

    }

    @Override
    public void terminateActivity() {
        getActivity().finish();
    }

    @Override
    public void movieCardClicked(View v, int position) {
        _presenter.movieCardClicked(position);
    }
}

package com.perrusset.romain.boxotop.UIL.Fragments;

import android.os.Bundle;
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
import com.perrusset.romain.boxotop.UIL.Adapter.MovieCardAdapter;
import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;
import com.perrusset.romain.boxotop.UIL.Movie;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoxOfficeFragment extends android.support.v4.app.Fragment implements BoxOfficeContract.View, MenuItem.OnActionExpandListener {

    private BoxOfficeContract.Presenter _presenter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private boolean IsSearchBarOpen;

    private ArrayList<Movie> listMovies;

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

        View v = inflater.inflate(R.layout.fragment_box_office, container, false);

        listMovies = new ArrayList<Movie>();

        mRecyclerView = v.findViewById((R.id.recyclerview_box_office));

        //set up the adapter
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MovieCardAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _presenter.start();
    }

    @Override
    public void notifyPresenterReady(ArrayList arrayList) {

        mAdapter.setmDataset(arrayList);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void notifyDataLoaded() {

    }

    @Override
    public void notifyDataRefreshed() {

    }

    @Override
    public void clearList() {
        listMovies.clear();
        mAdapter.setmDataset(listMovies);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSearchEmpty() {

    }

    @Override
    public void addList(ArrayList arrayList) {

        listMovies.addAll(arrayList);
        mAdapter.setmDataset (listMovies);
        mAdapter.notifyItemInserted(listMovies.size() - 1);
    }

    @Override
    public void startNextActivity(int movieID) {

    }

    @Override
    public void setListFull() {

    }

    @Override
    public void setListNotFull() {

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

    private void performSearch(String s) {
        //do the search
    }

    private void performQuickSearch(String s) {
        //do the search
    }

    private void onSearchBarFieldEmpty() {
        //clear the view
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        onCloseSearchBar();
        IsSearchBarOpen = false;
        return true;
    }


    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        onOpenSearchBar();
        IsSearchBarOpen = true;
        return true;
    }

    protected void onOpenSearchBar() {
        //clean the list to look empty
    }

    protected void onCloseSearchBar() {
        // put back the data
    }
}

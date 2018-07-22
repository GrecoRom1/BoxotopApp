package com.perrusset.romain.boxotop.UIL.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Adapter.MovieCardAdapter;
import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoxOfficeFragment extends Fragment implements BoxOfficeContract.View {

    private BoxOfficeContract.Presenter _presenter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private MovieCardAdapter mAdapter;

    public BoxOfficeFragment() {
    }

    public BoxOfficeFragment getInstance() {
        return new BoxOfficeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_box_office, container, false);

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


        _presenter.start(this);
        return v;
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
    public void startNextActivity(int movieID) {

    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        _presenter = (BoxOfficeContract.Presenter) presenter;
    }
}

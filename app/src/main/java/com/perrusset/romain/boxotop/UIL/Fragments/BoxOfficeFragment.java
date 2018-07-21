package com.perrusset.romain.boxotop.UIL.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Contracts.BaseContract;
import com.perrusset.romain.boxotop.UIL.Contracts.BoxOfficeContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoxOfficeFragment extends Fragment implements BoxOfficeContract.View {

    private BoxOfficeContract.Presenter _presenter;
    private RecyclerView recyclerView;

    public BoxOfficeFragment() {
    }

    public BoxOfficeFragment getInstance() {
        return new BoxOfficeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_box_office, container, false);
        recyclerView = v.findViewById((R.id.recyclerview_box_office));

        _presenter.start(this);
        return v;
    }

    @Override
    public void notifiyPresenterReady() {

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

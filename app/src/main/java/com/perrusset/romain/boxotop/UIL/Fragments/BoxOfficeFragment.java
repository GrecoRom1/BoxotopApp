package com.perrusset.romain.boxotop.UIL.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrusset.romain.boxotop.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoxOfficeFragment extends Fragment {

    public BoxOfficeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_box_office, container, false);
    }
}

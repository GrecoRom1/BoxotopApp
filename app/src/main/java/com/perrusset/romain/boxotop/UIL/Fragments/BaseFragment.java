package com.perrusset.romain.boxotop.UIL.Fragments;


import android.os.Bundle;

public abstract class BaseFragment extends android.support.v4.app.Fragment {

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Retain the instance of fragment
        setRetainInstance(true);
    }

}

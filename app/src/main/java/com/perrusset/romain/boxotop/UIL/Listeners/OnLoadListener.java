package com.perrusset.romain.boxotop.UIL.Listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class OnLoadListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLayoutManager;
    private LoadMoreEventListener mListener;

    public OnLoadListener(LinearLayoutManager layoutManager, LoadMoreEventListener listener) {
        mLayoutManager = layoutManager;
        mListener = listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = recyclerView.getAdapter().getItemCount();
        int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

        //If we are at the end of the list, we request to load more data
        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
            mListener.onLoadMoreData();
        }
    }
}

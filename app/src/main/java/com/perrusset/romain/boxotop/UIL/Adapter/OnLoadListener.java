package com.perrusset.romain.boxotop.UIL.Adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class OnLoadListener extends RecyclerView.OnScrollListener {

   private LinearLayoutManager LayoutManager;
   private LoadMoreEventListener mListener;

//public delegate void LoadMoreEventHandler(object sender, EventArgs e);
//public event LoadMoreEventHandler LoadMoreEvent;

    public OnLoadListener(LinearLayoutManager layoutManager,LoadMoreEventListener listener ) {
        LayoutManager = layoutManager;
        mListener = listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = recyclerView.getAdapter().getItemCount();
        int pastVisiblesItems = LayoutManager.findFirstVisibleItemPosition();

        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
            mListener.onLoadMoreData();
        }
    }
}

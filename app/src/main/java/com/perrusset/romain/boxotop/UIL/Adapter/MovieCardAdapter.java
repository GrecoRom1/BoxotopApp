package com.perrusset.romain.boxotop.UIL.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieCardAdapter extends RecyclerView.Adapter<MovieCardAdapter.ViewHolder> {

    private ArrayList<Movie> mDataset;
    private Context mContext;
    private static MovieCardClickListener mClickListener;

    // Lazy loading
    private boolean IsLoadingAdded = false;

    private boolean IsListFull;

    public void setListFull(boolean listFull) {
        IsListFull = listFull;
    }

    public boolean getIsListFull() {
        return IsListFull;
    }

    private final int ITEM = 0;
    private final int LOADING = 1;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleTextView;
        public ImageView mPosterImageView;
        public RelativeLayout mBackground;


        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.textview_listview_movieTitle);
            mPosterImageView = (ImageView) itemView.findViewById(R.id.imageview_listview_poster);
            mBackground = (RelativeLayout) itemView.findViewById(R.id.background_listview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.movieCardClicked(view,this.getLayoutPosition());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MovieCardAdapter(Context context, MovieCardClickListener clickListener) {
        mContext = context;
        mDataset = new ArrayList<Movie>();
        mClickListener = clickListener;
    }

    public void setmDataset(ArrayList<Movie> firstList) {
        mDataset = firstList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MovieCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        if (viewType == LOADING) {

            ProgressBar progressBar = new ProgressBar(context, null);
            ViewHolder viewHolder = (ViewHolder) new LoadingViewHolder(progressBar);
            return (new LoadingViewHolder(progressBar));
        } else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View contactView = inflater.inflate(R.layout.cells_listview_box_office, parent, false);
            ViewHolder vh = new ViewHolder(contactView);
            return vh;
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Chck if it is not the loading frame
        if (getItemViewType(position) == ITEM) {

            Movie movie = mDataset.get(position);

            //Set the title
            TextView textView = holder.mTitleTextView;
            textView.setText(movie.title);

            //Set the poster
            String url = "http://image.tmdb.org/t/p/w185" + movie.posterPath;
            ImageView imageView = holder.mPosterImageView;
            Picasso.with(mContext).load(url).into(imageView);

            //Set the background color
            if ((position % 2) == 0) {
                holder.mBackground.setBackgroundColor(Color.parseColor("#b6d7a8"));
            } else {
                holder.mBackground.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        else{
            ViewGroup.LayoutParams parameters = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            ProgressBar progressBar = ((LoadingViewHolder)holder).progressBar;
            progressBar.setIndeterminate(true);
            progressBar.setLayoutParams(parameters);
            progressBar.setPadding(8, 8, 8, 8);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mDataset.size() - 1 && IsLoadingAdded) ? LOADING : ITEM;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void addItem(Movie item) {
        mDataset.add(item);
        notifyItemInserted(mDataset.size() - 1);
    }

    private void removeItem(Movie item) {
        int position = mDataset.indexOf(item);
        if (position > -1) {
            mDataset.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoadingFooter() {
        IsLoadingAdded = true;
        addItem(new Movie());
    }

    public void removeLoadingFooter() {
        IsLoadingAdded = false;

        int position = mDataset.size() - 1;

        try {
            Movie item = mDataset.get(position);
            mDataset.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public void clearList(){
        if(mDataset.size()>0){
            removeLoadingFooter();
            mDataset.clear();
            notifyDataSetChanged();
        }
    }


    public void addAll(ArrayList<Movie> movies) {
        removeLoadingFooter();
        int pos = mDataset.size();

        mDataset.addAll(movies);
        //notifyItemRangeInserted(pos, mDataset.size());
        addLoadingFooter();
        notifyDataSetChanged();
    }

    class LoadingViewHolder extends MovieCardAdapter.ViewHolder {

        private ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);

            if (itemView.getClass() == ProgressBar.class) {
                progressBar = (ProgressBar) itemView;
            }
        }
    }
}


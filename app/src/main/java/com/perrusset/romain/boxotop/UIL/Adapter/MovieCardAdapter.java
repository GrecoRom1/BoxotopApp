package com.perrusset.romain.boxotop.UIL.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Listeners.MovieCardClickListener;
import com.perrusset.romain.boxotop.UIL.Model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieCardAdapter extends RecyclerView.Adapter<MovieCardAdapter.ViewHolder> {

    //region Static Properties
    private final static int ITEM = 0;
    private final static int LOADING = 1;
    //endregion

    //region Properties
    private ArrayList<Movie> mDataset;
    private Context mContext;
    private static MovieCardClickListener mClickListener;

    // Lazy loading
    private boolean mIsLoadingAdded = false;

    private boolean mIsListFull;

    public void setmIsListFull(boolean mIsListFull) {
        this.mIsListFull = mIsListFull;
    }

    public boolean getIsListFull() {
        return mIsListFull;
    }
    //endregion


    //region Adapter Constructor
    public MovieCardAdapter(Context context, MovieCardClickListener clickListener) {
        mContext = context;
        mDataset = new ArrayList<>();
        mClickListener = clickListener;
    }
    //endregion

    @NonNull
    @Override
    public MovieCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        //if it is a loading, we add the loading footer
        if (viewType == LOADING) {
            ProgressBar progressBar = new ProgressBar(context, null);
            return (new LoadingViewHolder(progressBar));
        }
        // Else we add the normal cells
        else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View contactView = inflater.inflate(R.layout.cells_listview_box_office, parent, false);
            return new ViewHolder(contactView);
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Check if it is not the loading frame
        if (getItemViewType(position) == ITEM) {

            Movie movie = mDataset.get(position);

            //Set the title
            TextView textView = holder.mTitleTextView;
            textView.setText(movie.getTitle());

            //Set the poster
            ImageView imageView = holder.mPosterImageView;
            Picasso.with(mContext).load(movie.getURLFormattedString()).into(imageView);

            //Set the background color
            if ((position % 2) == 0) {
                holder.mBackground.setBackgroundColor(ContextCompat.getColor(
                        mContext,R.color.boxOffice_cells_green));
            } else {
                holder.mBackground.setBackgroundColor(ContextCompat.getColor(
                        mContext,R.color.boxOffice_cells_white));
            }
        } else {
            ViewGroup.LayoutParams parameters = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            ProgressBar progressBar = ((LoadingViewHolder) holder).progressBar;
            progressBar.setIndeterminate(true);
            progressBar.setLayoutParams(parameters);
            progressBar.setPadding(8, 8, 8, 8);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mDataset.size() - 1 && mIsLoadingAdded) ? LOADING : ITEM;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //region Methods Manage Dataset
    private void addItem(Movie item) {
        mDataset.add(item);
        notifyItemInserted(mDataset.size() - 1);
        notifyDataSetChanged();
    }

    public void addLoadingFooter() {
        mIsLoadingAdded = true;
        addItem(new Movie());
    }

    public void removeLoadingFooter() {
        mIsLoadingAdded = false;
        int position = mDataset.size() - 1;

        //Security if the loading footer is not added
        try {
            mDataset.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void clearList() {
        if (mDataset.size() > 0) {
            removeLoadingFooter();
            mDataset.clear();
            notifyDataSetChanged();
        }
    }

    public void addAll(ArrayList<Movie> movies) {
        removeLoadingFooter();

        int startPos = mDataset.size();

        mDataset.addAll(movies);
        addLoadingFooter();
        notifyItemRangeInserted(startPos, movies.size());
    }

    //endregion

    //region ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //region Properties
        public TextView mTitleTextView;
        public ImageView mPosterImageView;
        public RelativeLayout mBackground;
        //endregion

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.textview_listview_movieTitle);
            mPosterImageView = itemView.findViewById(R.id.imageview_listview_poster);
            mBackground = itemView.findViewById(R.id.background_listview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.movieCardClicked(this.getLayoutPosition());
        }
    }
    //endregion

    //region LoadingViewHolder
    class LoadingViewHolder extends MovieCardAdapter.ViewHolder {

        private ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);

            if (itemView.getClass() == ProgressBar.class) {
                progressBar = (ProgressBar) itemView;
            }
        }
    }
    //endregion
}


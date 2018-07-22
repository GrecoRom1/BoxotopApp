package com.perrusset.romain.boxotop.UIL.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.perrusset.romain.boxotop.R;
import com.perrusset.romain.boxotop.UIL.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieCardAdapter extends RecyclerView.Adapter<MovieCardAdapter.ViewHolder> {

    private ArrayList<Movie> mDataset;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        public ImageView mPosterImageView;
        public RelativeLayout mBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.textview_listview_movieTitle);
            mPosterImageView = (ImageView) itemView.findViewById(R.id.imageview_listview_poster);
            mBackground = (RelativeLayout)itemView.findViewById(R.id.background_listview);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MovieCardAdapter(Context context) {
        mContext = context;
        mDataset = new ArrayList<Movie>();
    }

    public void setmDataset(ArrayList<Movie> firstList){
        mDataset = firstList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MovieCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.cells_listview_box_office, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Movie movie = mDataset.get(position);

        //Set the title
        TextView textView = holder.mTitleTextView;
        textView.setText(movie.title);

        //Set the poster
        String url = "http://image.tmdb.org/t/p/w185"+movie.posterPath;
        ImageView imageView = holder.mPosterImageView;
        Picasso.with(mContext).load(url).into(imageView);

        //Set the background color
        if((position%2)==0){
            holder.mBackground.setBackgroundColor(Color.parseColor("#b6d7a8"));
        }
        else{
            holder.mBackground.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
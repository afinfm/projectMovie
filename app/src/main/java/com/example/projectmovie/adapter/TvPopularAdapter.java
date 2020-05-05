package com.example.projectmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmovie.R;
import com.example.projectmovie.model.movie.TvPopularResultsItem;
import com.example.projectmovie.view.activity.DetailMovie;

import java.util.ArrayList;

public class TvPopularAdapter extends RecyclerView.Adapter<TvPopularAdapter.ViewHolder> {
    private ArrayList<TvPopularResultsItem> tvPopularItems = new ArrayList<>();
    private Context context;
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";

    public TvPopularAdapter(Context context){this.context = context;}

    public void setData(ArrayList<TvPopularResultsItem> items){
        tvPopularItems.clear();
        tvPopularItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvPopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvPopularAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(BASE_IMAGE_URL+tvPopularItems.get(position)
                .getPosterPath())
                .into(holder.ivThumb);

        holder.tvTitle.setText(tvPopularItems.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra("id", tvPopularItems.get(position).getId());
                intent.putExtra("backdrop", BASE_IMAGE_URL+tvPopularItems.get(position).getBackdropPath());
                intent.putExtra("thumb", BASE_IMAGE_URL+tvPopularItems.get(position).getPosterPath());
                intent.putExtra("rating", tvPopularItems.get(position).getVoteAverage());
                intent.putExtra("count", tvPopularItems.get(position).getVoteCount());
                intent.putExtra("judul", tvPopularItems.get(position).getName());
                intent.putExtra("desc", tvPopularItems.get(position).getOverview());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvPopularItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvTitle;
        CardView cvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItem = itemView.findViewById(R.id.itemlist_cv);
            ivThumb = itemView.findViewById(R.id.itemlist_iv_thumbnail);
            tvTitle = itemView.findViewById(R.id.itemlist_tv_title);
        }
    }
}

package com.example.projectmovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmovie.R;
import com.example.projectmovie.database.FavoritModel;
import com.example.projectmovie.model.movie.TvPopularResultsItem;

import java.util.ArrayList;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.ViewHolder> {
    private ArrayList<FavoritModel> favorititems = new ArrayList<>();
    private Context context;
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";

    public FavoritAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<FavoritModel> items){
        favorititems.clear();
        favorititems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoritAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_db, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+favorititems.get(position)
                .getThum())
                .into(holder.ivThumb);
        holder.tvJudul.setText(favorititems.get(position).getTitle());
        holder.tvDesc.setText(favorititems.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return favorititems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvDesc;
        ImageView ivThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivThumb = itemView.findViewById(R.id.itemDBlist_iv_thumbnail);
            tvJudul = itemView.findViewById(R.id.itemDBlist_tv_title);
            tvDesc  = itemView.findViewById(R.id.itemDBlist_tv_desc);

        }
    }
}

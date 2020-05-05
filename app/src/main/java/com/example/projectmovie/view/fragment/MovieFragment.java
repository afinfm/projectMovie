package com.example.projectmovie.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectmovie.R;
import com.example.projectmovie.adapter.MovieDiscoverAdapter;
import com.example.projectmovie.adapter.MovieTrendingAdapter;
import com.example.projectmovie.model.movie.MovieDiscoverResultsItem;
import com.example.projectmovie.model.movie.MovieTrendingResultsItem;
import com.example.projectmovie.view.viewmodel.MovieViewModel;
import com.example.projectmovie.view.viewmodel.TrendingViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private MovieDiscoverAdapter movieDiscoverAdapter;
    private MovieTrendingAdapter movieTrendingAdapter;
    private RecyclerView rvMovieDiscover, rvMovieTrending;
    private MovieViewModel movieViewModel;
    private TrendingViewModel trendingViewModel;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieDiscoverAdapter = new MovieDiscoverAdapter(getContext());
        movieDiscoverAdapter.notifyDataSetChanged();
        movieTrendingAdapter = new MovieTrendingAdapter(getContext());
        movieTrendingAdapter.notifyDataSetChanged();


        rvMovieDiscover = view.findViewById(R.id.fragmentslidemovie_rv);
        rvMovieDiscover.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMovieTrending = view.findViewById(R.id.fragmentmovie_rv);
        rvMovieTrending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.setMovieDiscover();
        movieViewModel.getMoviesDiscover().observe(this,getMovieDiscover);
        trendingViewModel = new ViewModelProvider(this).get(TrendingViewModel.class);
        trendingViewModel.setMovieTrending();
        trendingViewModel.getMoviesTrending().observe(this,getMovieTrending);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvMovieDiscover);
        rvMovieDiscover.setAdapter(movieDiscoverAdapter);
        rvMovieTrending.setAdapter(movieTrendingAdapter);
    }

    private Observer<ArrayList<MovieDiscoverResultsItem>> getMovieDiscover = new Observer<ArrayList<MovieDiscoverResultsItem>>() {
        @Override
        public void onChanged(ArrayList<MovieDiscoverResultsItem> movieDiscoverResultsItems) {
            if(movieDiscoverResultsItems != null){
                movieDiscoverAdapter.setData(movieDiscoverResultsItems);
            }
        }
    };

    private Observer<ArrayList<MovieTrendingResultsItem>> getMovieTrending = new Observer<ArrayList<MovieTrendingResultsItem>>() {
        @Override
        public void onChanged(ArrayList<MovieTrendingResultsItem> movieTrendingResultsItems) {
            if(movieTrendingResultsItems != null){
                movieTrendingAdapter.setData(movieTrendingResultsItems);
            }
        }
    };
}

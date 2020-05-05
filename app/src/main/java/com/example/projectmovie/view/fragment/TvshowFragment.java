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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectmovie.R;
import com.example.projectmovie.adapter.TvAiringTodayAdapter;
import com.example.projectmovie.adapter.TvPopularAdapter;
import com.example.projectmovie.model.movie.TvAiringTodayResultsItem;
import com.example.projectmovie.model.movie.TvPopularResultsItem;
import com.example.projectmovie.view.viewmodel.TvAirTodayViewModel;
import com.example.projectmovie.view.viewmodel.TvPopoularViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvshowFragment extends Fragment {
    private TvAiringTodayAdapter tvAiringTodayAdapter;
    private TvPopularAdapter tvPopularAdapter;
    private RecyclerView rvTvAirToday, rvTvPopular;
    private TvPopoularViewModel tvPopoularViewModel;
    private TvAirTodayViewModel tvAirTodayViewModel;

    public TvshowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvAiringTodayAdapter = new TvAiringTodayAdapter(getContext());
        tvAiringTodayAdapter.notifyDataSetChanged();
        tvPopularAdapter = new TvPopularAdapter(getContext());
        tvPopularAdapter.notifyDataSetChanged();


        rvTvAirToday = view.findViewById(R.id.fragmentslidemovie_rv);
        rvTvAirToday.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvTvPopular = view.findViewById(R.id.fragmentmovie_rv);
        rvTvPopular.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        tvAirTodayViewModel = new ViewModelProvider(this).get(TvAirTodayViewModel.class);
        tvAirTodayViewModel.setTvAirToday();
        tvAirTodayViewModel.getAiringToday().observe(this,getTvAiringToday);
        tvPopoularViewModel = new ViewModelProvider(this).get(TvPopoularViewModel.class);
        tvPopoularViewModel.setTvPopular();
        tvPopoularViewModel.getPopular().observe(this,getTvPopular);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvTvAirToday);
        rvTvAirToday.setAdapter(tvAiringTodayAdapter);
        rvTvPopular.setAdapter(tvPopularAdapter);
    }

    private Observer<ArrayList<TvAiringTodayResultsItem>> getTvAiringToday = new Observer<ArrayList<TvAiringTodayResultsItem>>() {
        @Override
        public void onChanged(ArrayList<TvAiringTodayResultsItem> movieDiscoverResultsItems) {
            if(movieDiscoverResultsItems != null){
                tvAiringTodayAdapter.setData(movieDiscoverResultsItems);
            }
        }
    };

    private Observer<ArrayList<TvPopularResultsItem>> getTvPopular = new Observer<ArrayList<TvPopularResultsItem>>() {
        @Override
        public void onChanged(ArrayList<TvPopularResultsItem> movieTrendingResultsItems) {
            if(movieTrendingResultsItems != null){
                tvPopularAdapter.setData(movieTrendingResultsItems);
            }
        }
    };

}

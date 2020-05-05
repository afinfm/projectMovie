package com.example.projectmovie.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectmovie.R;
import com.example.projectmovie.adapter.FavoritAdapter;
import com.example.projectmovie.database.AppDatabase;
import com.example.projectmovie.database.FavoritModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TagFragment extends Fragment {
    private RecyclerView rvFavorit;
    private FavoritAdapter favoritAdapter;
    private ArrayList<FavoritModel> listFavorit = new ArrayList<>();
    private AppDatabase appDatabase;

    public TagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavorit = view.findViewById(R.id.fragmenttag_rv);
        favoritAdapter = new FavoritAdapter(getContext());
        favoritAdapter.notifyDataSetChanged();

        if (appDatabase == null){
            appDatabase = AppDatabase.initDatabase(getContext());
        }

        listFavorit.addAll(appDatabase.favoritDAO().getFavorit());
        favoritAdapter.setData(listFavorit);

        rvFavorit.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavorit.setAdapter(favoritAdapter);

    }
}

package com.example.projectmovie.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectmovie.model.movie.MovieTrendingResponse;
import com.example.projectmovie.model.movie.MovieTrendingResultsItem;
import com.example.projectmovie.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingViewModel extends ViewModel {
    private ApiMain apiMain;
    private Call call;

    private MutableLiveData<ArrayList<MovieTrendingResultsItem>> listTrendingMovie = new MutableLiveData<>();

    public void setMovieTrending(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }
        apiMain.getApiTrending().getMovieTrending().enqueue(new Callback<MovieTrendingResponse>() {
            @Override
            public void onResponse(Call<MovieTrendingResponse> call, Response<MovieTrendingResponse> response) {
                MovieTrendingResponse responseTrending = response.body();
                if (responseTrending != null && responseTrending.getResults() != null){
                    ArrayList<MovieTrendingResultsItem> movieTrendingItems = responseTrending.getResults();
                    listTrendingMovie.postValue(movieTrendingItems);
                }
            }

            @Override
            public void onFailure(Call<MovieTrendingResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<MovieTrendingResultsItem>> getMoviesTrending(){
        return listTrendingMovie;
    }
}

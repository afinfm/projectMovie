package com.example.projectmovie.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectmovie.model.movie.MovieDiscoverResultsItem;
import com.example.projectmovie.model.movie.TvAiringTodayResponse;
import com.example.projectmovie.model.movie.TvAiringTodayResultsItem;
import com.example.projectmovie.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvAirTodayViewModel extends ViewModel {
    private ApiMain apiMain;
    private Call call;

    private MutableLiveData<ArrayList<TvAiringTodayResultsItem>> listAirToday = new MutableLiveData<>();

    public void setTvAirToday(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }
        apiMain.getApiTvAirToday().getTvAirToday().enqueue(new Callback<TvAiringTodayResponse>() {
            @Override
            public void onResponse(Call<TvAiringTodayResponse> call, Response<TvAiringTodayResponse> response) {
                TvAiringTodayResponse airingTodayResponse = response.body();
                if (airingTodayResponse != null && airingTodayResponse.getResults() != null){
                    ArrayList<TvAiringTodayResultsItem> airingTodayItems = airingTodayResponse.getResults();
                    listAirToday.postValue(airingTodayItems);
                }
            }

            @Override
            public void onFailure(Call<TvAiringTodayResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<TvAiringTodayResultsItem>> getAiringToday(){
        return listAirToday;
    }
}

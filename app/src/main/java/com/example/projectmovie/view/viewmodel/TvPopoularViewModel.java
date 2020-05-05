package com.example.projectmovie.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectmovie.model.movie.TvPopularResponse;
import com.example.projectmovie.model.movie.TvPopularResultsItem;
import com.example.projectmovie.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvPopoularViewModel extends ViewModel {
    private ApiMain apiMain;
    private Call call;

    private MutableLiveData<ArrayList<TvPopularResultsItem>> listPopular = new MutableLiveData<>();

    public void setTvPopular(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }
        apiMain.getApiTvPopular().getTvPopular().enqueue(new Callback<TvPopularResponse>() {
            @Override
            public void onResponse(Call<TvPopularResponse> call, Response<TvPopularResponse> response) {
                TvPopularResponse popularResponse = response.body();
                if (popularResponse != null && popularResponse.getResults() != null){
                    ArrayList<TvPopularResultsItem> popularItems = popularResponse.getResults();
                    listPopular.postValue(popularItems);
                }
            }

            @Override
            public void onFailure(Call<TvPopularResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<TvPopularResultsItem>> getPopular(){
        return listPopular;
    }
}

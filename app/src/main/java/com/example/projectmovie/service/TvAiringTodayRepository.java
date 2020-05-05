package com.example.projectmovie.service;

import com.example.projectmovie.model.movie.TvAiringTodayResponse;
import com.example.projectmovie.model.movie.TvPopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TvAiringTodayRepository {
    @GET("3/tv/airing_today?api_key=05faacecb1bb8a123ad56542b1708bad&language=en-US")
    Call<TvAiringTodayResponse> getTvAirToday();
}

package com.example.projectmovie.service;

import com.example.projectmovie.model.movie.MovieTrendingResponse;
import com.example.projectmovie.model.movie.TvPopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TvPopularRepository {
    @GET("3/tv/popular?api_key=05faacecb1bb8a123ad56542b1708bad&language=en-US")
    Call<TvPopularResponse> getTvPopular();
}

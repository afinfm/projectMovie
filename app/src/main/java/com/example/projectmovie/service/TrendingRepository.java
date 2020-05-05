package com.example.projectmovie.service;

import com.example.projectmovie.model.movie.MovieDiscoverResponse;
import com.example.projectmovie.model.movie.MovieTrendingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrendingRepository {
    @GET("3/trending/movie/week?api_key=05faacecb1bb8a123ad56542b1708bad")
    Call<MovieTrendingResponse> getMovieTrending();
}

package com.example.projectmovie.service;

import com.example.projectmovie.model.movie.MovieDiscoverResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieRepository {
    @GET("3/discover/movie?api_key=05faacecb1bb8a123ad56542b1708bad")
    Call<MovieDiscoverResponse> getMovieDiscover();
}

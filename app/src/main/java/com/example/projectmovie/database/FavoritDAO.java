package com.example.projectmovie.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoritDAO {
    @Insert
    long insertFavorit(FavoritModel favoritModel);

    @Query("SELECT * FROM dataFavorit")
    List<FavoritModel> getFavorit();

}

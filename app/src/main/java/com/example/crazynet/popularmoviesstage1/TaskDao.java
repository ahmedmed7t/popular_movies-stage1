package com.example.crazynet.popularmoviesstage1;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.crazynet.popularmoviesstage1.model.movieFavourate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyNet on 30/01/2019.
 */
@Dao
public interface TaskDao {
    @Query("SELECT * FROM favourate ")
    LiveData<List<movieFavourate>> loadAllMovies();

    @Insert
    void insertMovie(movieFavourate taskEntry);

    @Query("SELECT * FROM favourate WHERE id = :id")
    LiveData<movieFavourate> loadTaskById(int id);
}

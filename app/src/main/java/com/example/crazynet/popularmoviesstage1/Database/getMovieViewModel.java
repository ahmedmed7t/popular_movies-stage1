package com.example.crazynet.popularmoviesstage1.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.crazynet.popularmoviesstage1.AppDatabase;
import com.example.crazynet.popularmoviesstage1.model.movieFavourate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyNet on 30/01/2019.
 */

public class getMovieViewModel extends AndroidViewModel {

    private LiveData<List<movieFavourate>> favourate;

    public getMovieViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        favourate = database.taskDao().loadAllMovies();
    }

    public LiveData<List<movieFavourate>> getFavourate() {
        return favourate;
    }
}

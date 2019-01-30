package com.example.crazynet.popularmoviesstage1.Database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.crazynet.popularmoviesstage1.AppDatabase;
import com.example.crazynet.popularmoviesstage1.model.movieFavourate;

/**
 * Created by CrazyNet on 30/01/2019.
 */

public class getMovieDetailViewModel extends ViewModel {
    private LiveData<movieFavourate> taskEntry ;

    // TODO (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId

    public getMovieDetailViewModel(AppDatabase database , int mTaskId ) {
        taskEntry = database.taskDao().loadTaskById(mTaskId);
    }

    // TODO (7) Create a getter for the task variable

    public LiveData<movieFavourate> getTaskEntry() {
        return taskEntry;
    }
}

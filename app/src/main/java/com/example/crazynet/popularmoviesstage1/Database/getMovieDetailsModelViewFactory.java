package com.example.crazynet.popularmoviesstage1.Database;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.crazynet.popularmoviesstage1.AppDatabase;

/**
 * Created by CrazyNet on 30/01/2019.
 */

public class getMovieDetailsModelViewFactory extends ViewModelProvider.NewInstanceFactory {

    private AppDatabase mdatabase;
    private int id ;

    public getMovieDetailsModelViewFactory(AppDatabase database , int taskId) {
        mdatabase = database;
        id = taskId ;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new getMovieDetailViewModel(mdatabase,id);
    }
}

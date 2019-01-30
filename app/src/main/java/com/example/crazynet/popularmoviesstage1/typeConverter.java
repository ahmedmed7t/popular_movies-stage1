package com.example.crazynet.popularmoviesstage1;

import android.arch.persistence.room.TypeConverter;

import com.example.crazynet.popularmoviesstage1.model.reviewItem;
import com.example.crazynet.popularmoviesstage1.model.videoItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyNet on 30/01/2019.
 */

public class typeConverter {

    public static Gson gson = new Gson();

    @TypeConverter
    public static String movieToString(List<videoItem> arrayList){
        return gson.toJson(arrayList);
    }

    @TypeConverter
    public static List<videoItem> stringToMovie(String movie){
        Type listType = new TypeToken<List<videoItem>>() {}.getType();
        return gson.fromJson(movie , listType);
    }

    @TypeConverter
    public static String reviewToString(List<reviewItem> arrayList){
        return gson.toJson(arrayList);
    }

    @TypeConverter
    public static List<reviewItem> stringToReview(String movie){
        Type listType = new TypeToken<List<reviewItem>>() {}.getType();
        return gson.fromJson(movie , listType);
    }
}

package com.example.crazynet.popularmoviesstage1.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyNet on 30/01/2019.
 */
@Entity(tableName = "favourate")
public class movieFavourate {

    @PrimaryKey(autoGenerate = true)
    int id ;

    @ColumnInfo(name = "poster_path")
    String posterPath ;

    String title ;

    String date ;

    String rate;

    @ColumnInfo(name = "over_view")
    String overView;

    @ColumnInfo(name = "video_item_array_list")
    List<videoItem> videoItemArrayList;

    @ColumnInfo(name = "review_item_array_list")
    List<reviewItem> reviewItemArrayList;


    public movieFavourate(int id, String posterPath, String title, String date, String rate, String overView, List<videoItem> videoItemArrayList, List<reviewItem> reviewItemArrayList) {
        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
        this.date = date;
        this.rate = rate;
        this.overView = overView;
        this.videoItemArrayList = videoItemArrayList;
        this.reviewItemArrayList = reviewItemArrayList;
    }

    @Ignore
    public movieFavourate( String posterPath, String title, String date, String rate, String overView, List<videoItem> videoItemArrayList, List<reviewItem> reviewItemArrayList) {
        this.posterPath = posterPath;
        this.title = title;
        this.date = date;
        this.rate = rate;
        this.overView = overView;
        this.videoItemArrayList = videoItemArrayList;
        this.reviewItemArrayList = reviewItemArrayList;
    }

    public int getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getRate() {
        return rate;
    }

    public String getOverView() {
        return overView;
    }

    public List<videoItem> getVideoItemArrayList() {
        return videoItemArrayList;
    }

    public List<reviewItem> getReviewItemArrayList() {
        return reviewItemArrayList;
    }
}

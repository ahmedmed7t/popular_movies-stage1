package com.example.crazynet.popularmoviesstage1.homeActivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crazynet.popularmoviesstage1.AppDatabase;
import com.example.crazynet.popularmoviesstage1.AppExecutors;
import com.example.crazynet.popularmoviesstage1.Database.getMovieDetailViewModel;
import com.example.crazynet.popularmoviesstage1.Database.getMovieDetailsModelViewFactory;
import com.example.crazynet.popularmoviesstage1.R;
import com.example.crazynet.popularmoviesstage1.RetrofitWebService;
import com.example.crazynet.popularmoviesstage1.model.ReviewResponse;
import com.example.crazynet.popularmoviesstage1.model.VideoResponse;
import com.example.crazynet.popularmoviesstage1.model.movieFavourate;
import com.example.crazynet.popularmoviesstage1.model.reviewItem;
import com.example.crazynet.popularmoviesstage1.model.videoItem;
import com.example.crazynet.popularmoviesstage1.movieRecyclerAdapter;
import com.example.crazynet.popularmoviesstage1.reviewRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.title)
    TextView txt_title;
    @BindView(R.id.date)
    TextView txt_date;
    @BindView(R.id.rate)
    TextView txt_rate;
    @BindView(R.id.overView)
    TextView txt_overView;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.reviewRecycler)
    RecyclerView reviewRecyclerView;
    @BindView(R.id.button)
    Button button;

    String posterPath ,title ,date ,rate, overView;
    ArrayList<reviewItem> reviewItemArrayList;
    ArrayList<videoItem> videoItemArrayList;

    AppDatabase database;

    int id ;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        database = AppDatabase.getInstance(this);

       intent = getIntent();
      /* if(intent.hasExtra("id")) {


       }else if(intent.hasExtra("FavourateId")){
           id = intent.getIntExtra("FavourateId",-1);
           getDatailsFromRoom(id);
       }*/

       if(savedInstanceState == null & intent.hasExtra("id")){
           id = intent.getIntExtra("id", -1);
           viewMovie();
            viewReview();
            getTrailer();
       }else if(savedInstanceState == null & intent.hasExtra("FavourateId")){
           id = intent.getIntExtra("FavourateId",-1);
           getDatailsFromRoom(id);
           button.setVisibility(View.INVISIBLE);
       } else{
           onRestoreInstanceState(savedInstanceState);
           viewTrailer(videoItemArrayList);
           fillReviewRecyclerView(reviewItemArrayList);
       }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("posterPath",posterPath);
        outState.putString("rate",rate);
        outState.putString("overView",overView);
        outState.putString("title",title);
        outState.putString("date",date);
        outState.putParcelableArrayList("review", reviewItemArrayList);
        outState.putParcelableArrayList("video", videoItemArrayList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        posterPath = savedInstanceState.getString("posterPath");
        rate = savedInstanceState.getString("rate");
        overView = savedInstanceState.getString("overView");
        title = savedInstanceState.getString("title");
        date = savedInstanceState.getString("date");
        reviewItemArrayList = savedInstanceState.getParcelableArrayList("review");
        videoItemArrayList = savedInstanceState.getParcelableArrayList("video");
    }

    public void viewMovie(){
        posterPath = intent.getStringExtra("poster");
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w342"+posterPath)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        title = intent.getStringExtra("title");
        txt_title.setText(title);

        date = intent.getStringExtra("date");
        txt_date.setText("Release At : "+date);

        rate = intent.getStringExtra("rate");
        txt_rate.setText("Rate : "+rate);

        overView = intent.getStringExtra("overView");
        txt_overView.setText(overView);
    }

    public void getTrailer(){
        RetrofitWebService.getService().getVideo(id).enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
              videoItemArrayList = response.body().getResults();
              viewTrailer(videoItemArrayList);
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

            }
        });
    }

    public void viewTrailer(final ArrayList<videoItem> arrayList){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final movieRecyclerAdapter adapter = new movieRecyclerAdapter(arrayList, new movieRecyclerAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(int index) {
                String  id = arrayList.get(index).getKey();
                openYoutube(id);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    public void openYoutube(String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            this.startActivity(webIntent);
        } catch (ActivityNotFoundException ex) {
            this.startActivity(appIntent);

        }
    }

    public void viewReview(){
        RetrofitWebService.getService().getReview(id).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                reviewItemArrayList = response.body().getResults();
                fillReviewRecyclerView(reviewItemArrayList);
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });
    }

    public void fillReviewRecyclerView(ArrayList<reviewItem> arrayList){
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewRecyclerView.setHasFixedSize(true);
        reviewRecyclerView.setAdapter(new reviewRecyclerAdapter(arrayList));
    }

    @OnClick(R.id.button)
    public void addToFavourate (){
        final movieFavourate favourate = new movieFavourate(posterPath,title,date,rate,overView,videoItemArrayList,reviewItemArrayList);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.taskDao().insertMovie(favourate);
            }
        });
        Toast mToast = Toast.makeText(this, "film Added to Favourates", Toast.LENGTH_SHORT);
        mToast.show();
    }

    public void getDatailsFromRoom(int id){
        getMovieDetailsModelViewFactory factory = new getMovieDetailsModelViewFactory(database , id);
        getMovieDetailViewModel viewModel = ViewModelProviders.of(this,factory).get(getMovieDetailViewModel.class);
        viewModel.getTaskEntry().observe(this, new Observer<movieFavourate>() {
            @Override
            public void onChanged(@Nullable movieFavourate movieFavourate) {
             showAllScreen(movieFavourate);
            }
        });
    }

    public void showAllScreen(movieFavourate favourate){

        ArrayList<videoItem> arrayList =(ArrayList<videoItem>) favourate.getVideoItemArrayList();
        viewTrailer(arrayList);

        ArrayList<reviewItem> arrayList1 = (ArrayList<reviewItem>)  favourate.getReviewItemArrayList();
        fillReviewRecyclerView(arrayList1);

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w342"+favourate.getPosterPath())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        txt_title.setText(favourate.getTitle());

        txt_date.setText("Release At : "+favourate.getDate());

        txt_rate.setText("Rate : "+favourate.getRate());

        txt_overView.setText(favourate.getOverView());

    }
}
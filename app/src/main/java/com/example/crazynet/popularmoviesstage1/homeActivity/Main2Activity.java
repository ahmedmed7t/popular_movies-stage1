package com.example.crazynet.popularmoviesstage1.homeActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crazynet.popularmoviesstage1.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        String posterPath = intent.getStringExtra("poster");
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342"+posterPath).into(imageView);

        String title = intent.getStringExtra("title");
        txt_title.setText(title);

        String date = intent.getStringExtra("date");
        txt_date.setText("Release At : "+date);

        String rate = intent.getStringExtra("rate");
        txt_rate.setText("Rate : "+rate);

        String overView = intent.getStringExtra("overView");
        txt_overView.setText(overView);
    }

}

package com.example.crazynet.popularmoviesstage1.homeActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.crazynet.popularmoviesstage1.R;
import com.example.crazynet.popularmoviesstage1.RecyclerAdapter;
import com.example.crazynet.popularmoviesstage1.RetrofitWebService;
import com.example.crazynet.popularmoviesstage1.model.Response;
import com.example.crazynet.popularmoviesstage1.model.ResultsItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> posterPath = new ArrayList<>();
    Response response ;
    String type = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData(type);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();
       if(id == R.id.topRated) {
          type = "top_rated";
          getData(type);
       }
       else if(id == R.id.mostPopular){
         type = "popular";
         getData(type);
       }
        return super.onOptionsItemSelected(item);
    }

    public void getResponse(Response arrayList){
        response = arrayList ;
    }

    public void showPoster(){
        posterPath.clear();
        int length = response.getResults().size();
        ArrayList<ResultsItem> resultsItems = response.getResults();
        for(int i = 0 ; i < length ; i++)
        {
            posterPath.add(resultsItems.get(i).getPosterPath());
        }
        showdata();
    }

    public void showdata(){
        recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        RecyclerAdapter adt = new RecyclerAdapter(posterPath, new RecyclerAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(int index) {
               ResultsItem resultsItem = response.getResults().get(index);
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("poster",resultsItem.getPosterPath());
                intent.putExtra("title",resultsItem.getTitle());
                intent.putExtra("date",resultsItem.getReleaseDate());
                intent.putExtra("rate",String.valueOf(resultsItem.getVoteAverage()));
                intent.putExtra("overView",resultsItem.getOverview());

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adt);
    }

    public void getData(String name) {
        RetrofitWebService.getService().getPopular(name).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                getResponse(response.body());
                showPoster();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }
}

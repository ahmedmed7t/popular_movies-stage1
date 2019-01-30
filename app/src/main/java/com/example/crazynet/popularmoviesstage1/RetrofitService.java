package com.example.crazynet.popularmoviesstage1;


import com.example.crazynet.popularmoviesstage1.model.Response;
import com.example.crazynet.popularmoviesstage1.model.ReviewResponse;
import com.example.crazynet.popularmoviesstage1.model.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Medhat on 16/08/2018.
 */

public interface RetrofitService {

    String link = "?api_key=097bab15f5e0951d3d87108b90a463cd";

     @GET("movie/{name}"+link)
     Call<Response> getPopular (@Path("name") String name);

    @GET("movie/{id}/videos"+link)
    Call<VideoResponse> getVideo (@Path("id") int id);

    @GET("movie/{id}/reviews"+link)
    Call<ReviewResponse> getReview (@Path("id") int id);

}

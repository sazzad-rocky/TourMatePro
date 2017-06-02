package com.example.afiqur.tourmatepro.NearBy;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by PIASH on 5/1/2017.
 */

public interface RetrofitMaps {



    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */
    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
    Call<Example> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);

}

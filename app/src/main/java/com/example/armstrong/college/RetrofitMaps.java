package com.example.armstrong.college;

import com.example.armstrong.college.POJO.Example;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by armstrong on 3/27/2017.
 */
public interface RetrofitMaps {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */
    @GET("api/geocode/json?key=AIzaSyBqNazde1lFAdacgH4mkz5D9AiAndR79SY")
    Call<Example> getDistanceDuration(@Query("units") String units, @Query("origin") String origin, @Query("destination") String destination, @Query("mode") String mode);

}
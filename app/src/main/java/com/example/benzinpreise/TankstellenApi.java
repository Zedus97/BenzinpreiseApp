package com.example.benzinpreise;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TankstellenApi {

    String BASE_URL = "https://creativecommons.tankerkoenig.de/";
    String API_KEY = "50d25698-e799-22df-c47c-833b7ae62b8b";

    @GET("json/list.php")
    Call<Tankstellen> getTankstellen (@Query("lat") String lat,
                                     @Query("lng") String lng,
                                     @Query("rad") String umkreis,
                                     @Query("type") String type,
                                     @Query("sort") String filter,
                                     @Query("apikey") String apikey);

}

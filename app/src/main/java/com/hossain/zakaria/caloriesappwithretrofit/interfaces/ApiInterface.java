package com.hossain.zakaria.caloriesappwithretrofit.interfaces;

import com.hossain.zakaria.caloriesappwithretrofit.models.Calorie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("get_calories.php")
    Call<List<Calorie>> getCaloriesInfo(@Query("item_type") String item_type);
}

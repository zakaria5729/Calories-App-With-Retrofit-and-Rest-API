package com.hossain.zakaria.caloriesappwithretrofit.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Calorie implements Serializable {

    @SerializedName("calorie")
    private int calorie;

    @SerializedName("imagepath")
    private String imagePath;

    @SerializedName("name")
    private String name;

    public int getCalorie() {
        return calorie;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }
}

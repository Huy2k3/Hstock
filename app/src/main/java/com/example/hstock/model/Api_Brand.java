package com.example.hstock.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_Brand {
    @GET("server/fashionBrand.php")
    Call<List<Model_Brand>> getBrand();
}

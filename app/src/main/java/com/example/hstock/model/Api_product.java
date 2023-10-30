package com.example.hstock.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api_product {
    @GET("server/product.php")
    Call<List <Model_product> > getProduct();


}


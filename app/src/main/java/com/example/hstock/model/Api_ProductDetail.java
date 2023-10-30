package com.example.hstock.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api_ProductDetail {
    @GET("server/productCategory.php")
    Call<Model_ProductDetail> getProductDetail(@Query("id") int id);

    @GET("productCategory/size")
    Call<List<String>> getSize();

}



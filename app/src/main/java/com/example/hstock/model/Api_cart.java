package com.example.hstock.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api_cart {
    @POST("server/getAccount.php")
    Call<Model_Cart> postCart(@Body Model_Cart modelCart );
}

package com.example.hstock.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api_User {
    @GET("server/user.php")
    Call<List<Model_User>> getUser();

    @FormUrlEncoded
    @POST("server/getAccount.php")
    Call<Model_User> dangKy(
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("addRess") String addRess,
            @Field("phoneNumber") String phoneNumber,
            @Field("userName") String userName,
            @Field("userPassword") String userPassword
    );
//    Call<Model_User> postUser(@Body Model_User model_user );

}

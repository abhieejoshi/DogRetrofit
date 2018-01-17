package com.example.abhinav.dogsretrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by abhinav on 8/1/18.
 */

public interface SignUpInterface {

    @POST("signup.php")
    @FormUrlEncoded
    Call<SignUpDataModel> sendData(@Field("email")String email,
                        @Field("pass") String pass);
}

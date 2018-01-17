package com.example.abhinav.dogsretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhinav on 8/1/18.
 */

public class ApiClient {
    public static final String Base_url="https://dog.ceo/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

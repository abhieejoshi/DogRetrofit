package com.example.abhinav.dogsretrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhinav on 8/1/18.
 */

public class SignUpClient {
    public static final String Base_url="http://192.168.0.10/DogRetrofit/";
    private static Retrofit retrofit=null;

//    private static Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}


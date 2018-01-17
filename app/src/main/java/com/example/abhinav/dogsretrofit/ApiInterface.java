package com.example.abhinav.dogsretrofit;



import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by abhinav on 8/1/18.
 */


public interface ApiInterface {

    @GET("api/breeds/list")
    public Call<Dogs> getDogs();

   @GET("api/breed/{breedname}/images/random")
   public Call<RandomDogImage> getBreedImage(@Path("breedname") String breedname);
}

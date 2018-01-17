package com.example.abhinav.dogsretrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abhinav on 8/1/18.
 */

public class SignUpDataModel {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("pass")
    @Expose
    private String pass;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}

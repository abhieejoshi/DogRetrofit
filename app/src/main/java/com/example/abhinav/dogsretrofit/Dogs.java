package com.example.abhinav.dogsretrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dogs {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private List<String> message = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }


}
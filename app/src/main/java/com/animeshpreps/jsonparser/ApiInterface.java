package com.animeshpreps.jsonparser;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/api/challenges/json/age-counting")
    Call<ModelUser> doGetListResources();

}

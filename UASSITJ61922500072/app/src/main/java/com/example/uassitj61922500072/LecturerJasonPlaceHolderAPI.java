package com.example.uassitj61922500072;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface LecturerJasonPlaceHolderAPI {
    @GET("lecturer.php")
    Call<List<com.example.uassitj61922500072.tampil>> getPosts(

    );
    @GET("lecturer.php")
    Call<List<List<com.example.uassitj61922500072.tampil>>> getPosts(@QueryMap Map<String, String> parameters);
}

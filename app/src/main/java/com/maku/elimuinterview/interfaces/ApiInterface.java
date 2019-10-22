package com.maku.elimuinterview.interfaces;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("users/ma-za-kpe")
    Call<com.maku.githubapitestapp.models.Profile> getProfileInfo();

    @GET("users/ma-za-kpe/repos")
    Call<ArrayList<com.maku.githubapitestapp.models.MyRepoList>> getRepoList();

}

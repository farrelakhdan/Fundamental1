package com.example.fundamental1.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @GET("search/users")
    Call<GithubResponse> getList(@Query("q") String q);

    @GET("users/{username}")
    Call<DetailResponse> getDetailUser(@Path("username") String username);

    @GET("users/{username}/following")
    Call<List<ItemsItem>> getFollowing(@Path("username") String username);

    @GET("users/{username}/followers")
    Call<List<ItemsItem>> getFollowers(@Path("username") String username);
}

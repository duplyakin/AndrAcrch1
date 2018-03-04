package com.vlad.archsample.common;

import com.vlad.archsample.sample1_godObject.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Vlad on 04.02.2018.
 */

public interface RestAdapter {

    String ENDPOINT = "https://api.github.com/";

    @GET("users/{username}")
    Call<User> user(@Path("username") String username);

    @GET("users")
    Call<List<User>> users();
}

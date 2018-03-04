package com.vlad.archsample.sample1_godObject.model;

/**
 * Created by Vlad on 04.02.2018.
 */


import com.google.gson.annotations.SerializedName;

/**
 * "login": "mojombo",
 * "id": 1,
 * "avatar_url": "https://avatars0.githubusercontent.com/u/1?v=4"
 */
public class User {
    public long id;
    public String login;
    public @SerializedName("avatar_url") String avatarUrl;
}

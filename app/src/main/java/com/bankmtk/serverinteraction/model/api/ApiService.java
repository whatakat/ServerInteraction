package com.bankmtk.serverinteraction.model.api;

import com.bankmtk.serverinteraction.entity.Repository;
import com.bankmtk.serverinteraction.entity.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiService {
    @GET("users/{user}")
    Single<User> getUser(@Path("user") String userName);

    @GET
    Single<List<Repository>> getUserRepos(@Url String url);
}

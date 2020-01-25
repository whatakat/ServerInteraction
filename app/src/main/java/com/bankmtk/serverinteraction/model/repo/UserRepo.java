package com.bankmtk.serverinteraction.model.repo;

public class UserRepo {
    public Single<User> getUser(String username)
    {
        return ApiHolder.getApi().getUser(username).subscribeOn(Schedulers.io());
    }

    public Single<List<Repository>> getUserRepos(String url)
    {
        return ApiHolder.getApi().getUserRepos(url).subscribeOn(Schedulers.io());
    }
}

package com.bankmtk.serverinteraction.model.repo;

public class RoomUserRepo {
    public Single<User> getUser(String username) {
        if (NetworkStatus.isOnline()) {
            return ApiHolder.getApi().getUser(username)
                    .subscribeOn(Schedulers.io())
                    .map(user -> {

                        RoomUser roomUser = UserDatabase.getInstance().getUserDao()
                                .findByLogin(username);

                        if (roomUser == null) {
                            roomUser = new RoomUser();
                            roomUser.setLogin(username);
                        }

                        roomUser.setAvatarUrl(user.getAvatarUrl());
                        roomUser.setReposUrl(user.getReposUrl());

                        UserDatabase.getInstance().getUserDao()
                                .insert(roomUser);

                        return user;
                    });
        } else {
            return Single.create(emitter -> {
                RoomUser roomUser = UserDatabase.getInstance().getUserDao()
                        .findByLogin(username);

                if (roomUser == null) {
                    emitter.onError(new RuntimeException("No such user in cache"));
                } else {
                    emitter.onSuccess(new User(roomUser.getLogin(), roomUser.getAvatarUrl(), roomUser.getReposUrl()));
                }
            }).subscribeOn(Schedulers.io()).cast(User.class);
        }
    }

    public Single<List<Repository>> getUserRepos(User user) {
        if (NetworkStatus.isOnline()) {
            return ApiHolder.getApi().getUserRepos(user.getReposUrl())
                    .subscribeOn(Schedulers.io())
                    .map(repos -> {

                        RoomUser roomUser = UserDatabase.getInstance().getUserDao()
                                .findByLogin(user.getLogin());

                        if (roomUser == null) {
                            roomUser = new RoomUser();
                            roomUser.setLogin(user.getLogin());
                            roomUser.setAvatarUrl(user.getAvatarUrl());
                            roomUser.setReposUrl(user.getReposUrl());
                            UserDatabase.getInstance()
                                    .getUserDao()
                                    .insert(roomUser);
                        }


                        if (!repos.isEmpty()) {
                            List<RoomRepository> roomRepositories = new ArrayList<>();
                            for (Repository repository : repos) {
                                RoomRepository roomRepository = new RoomRepository(repository.getId(), repository.getName(), user.getLogin());
                                roomRepositories.add(roomRepository);
                            }

                            UserDatabase.getInstance()
                                    .getRepositoryDao()
                                    .insert(roomRepositories);
                        }

                        return repos;
                    });
        } else {

            return Single.create(emitter -> {
                RoomUser roomUser = UserDatabase.getInstance()
                        .getUserDao()
                        .findByLogin(user.getLogin());

                if(roomUser == null){
                    emitter.onError(new RuntimeException("No such user in cache"));
                } else {
                    List<RoomRepository> roomRepositories = UserDatabase.getInstance().getRepositoryDao()
                            .getAll();

                    List<Repository> repos = new ArrayList<>();
                    for (RoomRepository roomRepository: roomRepositories){
                        repos.add(new Repository(roomRepository.getId(), roomRepository.getName()));
                    }

                    emitter.onSuccess(repos);
                }
            }).subscribeOn(Schedulers.io()).cast((Class<List<Repository>>)(Class)List.class);
        }
    }
}

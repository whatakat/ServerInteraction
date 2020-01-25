package com.bankmtk.serverinteraction.presenter;

import com.bankmtk.serverinteraction.mvp.view.item.RepoItemView;

import io.reactivex.Scheduler;
import io.reactivex.subjects.PublishSubject;

public class MainPresenter MvpPresenter<MainView> {
public class RepoListPresenter implements IRepoListPresenter {
    PublishSubject<RepoItemView> clickSubject = PublishSubject.create();

    @Override
    public PublishSubject<RepoItemView> getClickSubject() {
        return clickSubject;
    }

    @Override
    public void bindView(RepoItemView view) {
        Repository repository = user.getRepos().get(view.getPos());
        view.setTitle(repository.getName());
    }

    @Override
    public int getRepoCount() {
        return user == null || user.getRepos() == null ? 0 : user.getRepos().size();
    }
}

    public RepoListPresenter repoListPresenter = new RepoListPresenter();

    private Scheduler mainThreadScheduler;
    private RealmUserRepo userRepo;

    private User user;

    public MainPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        userRepo = new RealmUserRepo();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadInfo();
    }

    @SuppressLint("CheckResult")
    public void loadInfo() {
        getViewState().showLoading();
        userRepo.getUser("googlesamples")
                .observeOn(mainThreadScheduler)
                .subscribe(user -> {
                    this.user = user;
                    getViewState().showAvatar(user.getAvatarUrl());
                    getViewState().setUsername(user.getLogin());
                    userRepo.getUserRepos(user)
                            .observeOn(mainThreadScheduler)
                            .subscribe(repositories -> {
                                this.user.setRepos(repositories);
                                getViewState().hideLoading();
                                getViewState().updateRepoList();
                            }, throwable -> {
                                Timber.e(throwable, "Failed to get user repos");
                                getViewState().showError(throwable.getMessage());
                            });


                }, throwable -> {
                    Timber.e(throwable, "Failed to get user");
                    getViewState().showError(throwable.getMessage());
                });
    }
}

package com.bankmtk.serverinteraction.mvp;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView{
    void showAvatar(String avatarUrl);
    void showError(String message);
    void setUsername(String username);
    void showLoading();
    void hideLoading();
    void updateRepoList();
    void init();
}

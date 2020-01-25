package com.bankmtk.serverinteraction.presenter.list;

import com.bankmtk.serverinteraction.mvp.view.item.RepoItemView;

import io.reactivex.subjects.PublishSubject;

public interface IRepoListPresenter {

    PublishSubject<RepoItemView> getClickSubject();
    void bindView(RepoItemView rowView);
    int getRepoCount();
}

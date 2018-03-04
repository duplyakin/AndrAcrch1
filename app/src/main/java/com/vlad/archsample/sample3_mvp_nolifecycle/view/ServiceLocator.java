package com.vlad.archsample.sample3_mvp_nolifecycle.view;

public class ServiceLocator {

    private MainPresenter3 mainPresenter3;

    MainPresenter3 getMainPresenter3(MainActivity3 view) {
        if (mainPresenter3 == null) {
            mainPresenter3 = new MainPresenter3();
        }
        mainPresenter3.attachView(view);
        return mainPresenter3;
    }
}

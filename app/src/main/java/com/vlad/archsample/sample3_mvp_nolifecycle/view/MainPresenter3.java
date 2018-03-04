package com.vlad.archsample.sample3_mvp_nolifecycle.view;

import com.vlad.archsample.MainApplication;
import com.vlad.archsample.common.RestAdapter;
import com.vlad.archsample.common.User;
import com.vlad.archsample.common.UserListAdapter;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import timber.log.Timber;

class MainPresenter3 {

    private WeakReference<MainActivity3> view;

    private UserListAdapter listAdapter = new UserListAdapter();
    private RestAdapter restAdapter;

    MainPresenter3() {
    }

    void attachView(MainActivity3 view) {
        this.view = new WeakReference<MainActivity3>(view);
        restAdapter = ((MainApplication) this.view.get().getApplication()).getRestAdapter();
    }

    void detachView() {
        view.clear();
        view = null;
    }

    void start() {
        this.view.get().setAdapter(listAdapter);

        if (listAdapter.getItemCount() > 0) {
            invalidateView();
        } else {
            loadUsers();
        }
    }

    void onViewDestroy() {
        detachView();
    }

    private void loadUsers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<User> users = restAdapter.users().execute().body();
                    listAdapter.items = users;
                    invalidateView();
                } catch (IOException e) {
                    Timber.e(e, "Network error");
                }
            }
        }).start();
    }

    private void invalidateView() {
        view.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.notifyDataSetChanged();
            }
        });
    }
}

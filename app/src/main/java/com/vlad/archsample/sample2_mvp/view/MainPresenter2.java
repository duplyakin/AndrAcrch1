package com.vlad.archsample.sample2_mvp.view;

import com.vlad.archsample.MainApplication;
import com.vlad.archsample.common.RestAdapter;
import com.vlad.archsample.common.User;
import com.vlad.archsample.common.UserListAdapter;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import timber.log.Timber;

public class MainPresenter2 {

    private WeakReference<MainActivity2> view;

    private UserListAdapter listAdapter;
    private RestAdapter restAdapter;

    MainPresenter2(MainActivity2 view) {
        this.view = new WeakReference<MainActivity2>(view);
        listAdapter = new UserListAdapter();
        restAdapter = ((MainApplication) this.view.get().getApplication()).getRestAdapter();
    }

    void onCreate() {
        this.view.get().setAdapter(listAdapter);
    }

    void onStart() {
        loadUsers();
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

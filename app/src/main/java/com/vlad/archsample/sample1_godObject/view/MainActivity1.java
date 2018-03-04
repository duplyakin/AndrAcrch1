package com.vlad.archsample.sample1_godObject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vlad.archsample.MainApplication;
import com.vlad.archsample.R;
import com.vlad.archsample.common.UserListAdapter;
import com.vlad.archsample.common.RestAdapter;
import com.vlad.archsample.sample1_godObject.model.User;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity1 extends AppCompatActivity {

    @BindView(R.id.rv_items) RecyclerView listOfUsers;

    UserListAdapter listAdapter;
    RestAdapter restAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restAdapter = ((MainApplication) getApplication()).getRestAdapter();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listAdapter = new UserListAdapter();
        listOfUsers.setAdapter(listAdapter);
        listOfUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.notifyDataSetChanged();
            }
        });
    }
}

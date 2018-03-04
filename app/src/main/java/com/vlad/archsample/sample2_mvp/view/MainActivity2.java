package com.vlad.archsample.sample2_mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.vlad.archsample.R;
import com.vlad.archsample.common.UserListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.rv_items) RecyclerView listOfUsers;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private MainPresenter2 presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Sample 2 - MVP with lifecycle");

        presenter = new MainPresenter2(this);
        presenter.onCreate();

        listOfUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    void setAdapter(UserListAdapter adapter) {
        listOfUsers.setAdapter(adapter);
    }
}

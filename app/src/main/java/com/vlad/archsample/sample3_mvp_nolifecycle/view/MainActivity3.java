package com.vlad.archsample.sample3_mvp_nolifecycle.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.vlad.archsample.MainApplication;
import com.vlad.archsample.R;
import com.vlad.archsample.common.UserListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity3 extends AppCompatActivity {

    @BindView(R.id.rv_items) RecyclerView listOfUsers;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private MainPresenter3 presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Sample 3 - MVP");

        presenter = ((MainApplication) getApplication()).getServiceLocator().getMainPresenter3(this);

        listOfUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDestroy();
    }

    void setAdapter(UserListAdapter adapter) {
        listOfUsers.setAdapter(adapter);
    }
}

package com.vlad.archsample.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad.archsample.R;
import com.vlad.archsample.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 04.02.2018.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListViewHolder> {

    List<User> items = new ArrayList<>();

    @Override
    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_user, parent, false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserListViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

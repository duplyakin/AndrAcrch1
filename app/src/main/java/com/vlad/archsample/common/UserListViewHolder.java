package com.vlad.archsample.common;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vlad.archsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vlad on 04.02.2018.
 */

public class UserListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_image) SimpleDraweeView avatar;
    @BindView(R.id.tv_name) TextView name;

    public UserListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(User user) {
        avatar.setImageURI(Uri.parse(user.avatarUrl));
        name.setText(user.login);
    }
}

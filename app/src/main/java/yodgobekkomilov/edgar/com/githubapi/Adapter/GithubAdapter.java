package yodgobekkomilov.edgar.com.githubapi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import yodgobekkomilov.edgar.com.githubapi.R;
import yodgobekkomilov.edgar.com.githubapi.pojo.Github;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.CustomViewHolder> {

    private List<Github> githubs;

    public GithubAdapter(List<Github> githubs) {
        this.githubs = githubs;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.github_list, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Github github = githubs.get(position);
        holder.userName.setText(github.getName());
        holder.avatar.setText(github.getAvatarUrl());
        holder.followers.setText(String.valueOf(github.getFollowers()));
        holder.repositories.setText(String.valueOf(github.getPublicRepos()));

    }

    @Override
    public int getItemCount() {
        return githubs.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, avatar, followers, repositories;

        public CustomViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.userName);
            avatar = (TextView) view.findViewById(R.id.avatar);
            followers = (TextView) view.findViewById(R.id.followers);
            repositories = (TextView) view.findViewById(R.id.repositories);

        }
    }
}
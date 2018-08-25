package yodgobekkomilov.edgar.com.githubapi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import yodgobekkomilov.edgar.com.githubapi.R;
import yodgobekkomilov.edgar.com.githubapi.pojo.Github;
import yodgobekkomilov.edgar.com.githubapi.pojo.GithubRepo;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.CustomViewHolder> {

    private List<Github> githubs;
    private Context context;

    private List<Github> githubListFiltered;
    GithubRepo githubRepo;


    public GithubAdapter(GithubRepo githubRepo) {
        this.githubRepo = githubRepo;

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.github_list, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        GithubRepo github = githubListFiltered.get(position);
        holder.repName.setText(github.getName());
        holder.gitUrl.setText(github.getGitUrl());
        // To fit image into imageView


        // To prevent fade animation
        holder.gitDescription.setText(String.valueOf(github.getDescription()));
        holder.createdDate.setText(String.valueOf(github.getCreatedAt()));
        holder.forksCount.setText(String.valueOf(github.getForksCount()));

    }

    @Override
    public int getItemCount() {

        githubListFiltered = Collections.singletonList(new Github());
        return githubListFiltered.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView repName, gitUrl, gitDescription, createdDate, forksCount;


        public CustomViewHolder(View view) {
            super(view);
            repName = (TextView) view.findViewById(R.id.repName);
            gitUrl = (TextView) view.findViewById(R.id.gitUrl);
            gitDescription = (TextView) view.findViewById(R.id.gitDescription);
            createdDate = (TextView) view.findViewById(R.id.createdDate);
            forksCount = (TextView) view.findViewById(R.id.forksCount);


        }
    }
}


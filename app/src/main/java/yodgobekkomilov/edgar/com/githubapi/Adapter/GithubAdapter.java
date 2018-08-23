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

 public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.CustomViewHolder>  implements Filterable{

    private List<Github> githubs;
    private Context context;
    private GithubAdapterListener listener;
    private List<Github> githubListFiltered;



    public GithubAdapter(List<Github> githubs, GithubAdapterListener listener) {

        this.githubs = githubs;
        this.listener = listener;


    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.github_list, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Github github = githubListFiltered.get(position);
        holder.userName.setText(github.getName());

        Picasso.with(holder.avatar.getContext())
                .load(github.getAvatarUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).resize(600, 200).centerCrop().into(holder.avatar);
                // To fit image into imageView


        // To prevent fade animation
        holder.followers.setText(String.valueOf(github.getFollowers()));
        holder.repositories.setText(String.valueOf(github.getPublicRepos()));

    }

    @Override
    public int getItemCount() {

             githubListFiltered = Collections.singletonList(new Github());
        return githubListFiltered.size();
    }






    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView userName,  followers, repositories;
        public ImageView avatar;

        public CustomViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.userName);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            followers = (TextView) view.findViewById(R.id.followers);
            repositories = (TextView) view.findViewById(R.id.repositories);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onContactSelected(githubListFiltered.get(getAdapterPosition()));
                }
            });

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    githubListFiltered = githubs;
                } else {
                    List<Github> filteredList = new ArrayList<>();
                    for (Github row : githubs) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ((row.getName().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getAvatarUrl().contains(charSequence)))
                            row.getFollowers().intValue(),  row.getReposUrl().contains(charSequence)){
                                    filteredList.add(row);
                            }

                    }

                    githubListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = githubs;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                 githubListFiltered = (List<Github>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface GithubAdapterListener {
        void onContactSelected(Github github);
    }
}


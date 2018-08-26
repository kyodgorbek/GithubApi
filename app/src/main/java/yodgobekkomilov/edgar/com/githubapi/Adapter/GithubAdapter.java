package yodgobekkomilov.edgar.com.githubapi.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import yodgobekkomilov.edgar.com.githubapi.R;
import yodgobekkomilov.edgar.com.githubapi.pojo.Github;
import yodgobekkomilov.edgar.com.githubapi.pojo.GithubRepo;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.CustomViewHolder> {

    private List<Github> githubs;
    private Context context;

    private List<Github> githubListFiltered;
    GithubRepo[] githubRepo;


    public GithubAdapter(GithubRepo[] githubRepo) {
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

        GithubRepo github = githubRepo[position];
        holder.repName.setText(github.getName());
        holder.gitUrl.setText(github.getGitUrl());
        // To fit image into imageView


        // To prevent fade animation
        holder.gitDescription.setText(String.valueOf(github.getDescription()));
        holder.createdDate.setText(getConvertedDate(github.getCreatedAt()));
        holder.forksCount.setText(String.valueOf(github.getForksCount()));

    }

    @Override
    public int getItemCount() {

        return githubRepo.length;
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public String convertDate(String date) {
//
//        String result = "";
//        org.joda.time.format.DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
//        DateTime from = format.parseDateTime(date);
//        DateTime now = new DateTime();
//        Period period = new Period(from, now);
//        PeriodFormatter formatter = new PeriodFormatterBuilder()
//                .appendSeconds().appendSuffix(" Sekonds ago\n")
//                .appendMinutes().appendSuffix(" Minutes ago\n")
//                .appendHours().appendSuffix(" Hours ago\n")
//                .appendDays().appendSuffix(" Days ago\n")
//                .appendWeeks().appendSuffix(" Weeks ago\n")
//                .appendMonths().appendSuffix(" Months ago\n")
//                .appendYears().appendSuffix(" Years ago\n")
//                .printZeroNever()
//                .toFormatter();
//        if (period.getDays()<1) {
//            result = formatter.print(period);
//        } else {
//            org.joda.time.format.DateTimeFormatter format24hMore = DateTimeFormat.forPattern("yyyy-MM-dd");
//            result = format24hMore.print(from);
//
//        }
//        return result;
//    }
   public String getConvertedDate(String date) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("M/dd -yy, HH:mm");

        Date mDate = null;
        try {
            mDate = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(mDate);


        return outputDateStr;
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


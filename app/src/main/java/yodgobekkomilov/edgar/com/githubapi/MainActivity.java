package yodgobekkomilov.edgar.com.githubapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yodgobekkomilov.edgar.com.githubapi.Adapter.GithubAdapter;

import yodgobekkomilov.edgar.com.githubapi.pojo.Github;
import yodgobekkomilov.edgar.com.githubapi.pojo.GithubClient;
import yodgobekkomilov.edgar.com.githubapi.pojo.GithubService;


public class MainActivity extends AppCompatActivity{
private Github githubArrayList;
private ProgressDialog pDialog;
private RecyclerView recyclerView;
private GithubAdapter eAdapter;




@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        //Creating an object of our api interface
        GithubService api = GithubClient.getApiService();

        /**
         * Calling JSON
         */
        Call<Github> call = api.getData();

        /**
         * Enqueue Callback will be call when get response...
         */
        call.enqueue(new Callback<Github>() {
@Override
public void onResponse(Call<Github> call, Response<Github> response) {
        //Dismiss Dialog
        pDialog.dismiss();

        if (response.isSuccessful()) {
        /**
         * Got Successfully
         */
        githubArrayList = response.body();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        eAdapter = new GithubAdapter(Collections.singletonList((Github)  githubArrayList ));
        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(eLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eAdapter);
        }
        }

@Override
public void onFailure(Call<Github> call, Throwable t) {
        pDialog.dismiss();
        }
        });
        }
        }

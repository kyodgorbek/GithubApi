package yodgobekkomilov.edgar.com.githubapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yodgobekkomilov.edgar.com.githubapi.Adapter.GithubAdapter;

import yodgobekkomilov.edgar.com.githubapi.pojo.Github;
import yodgobekkomilov.edgar.com.githubapi.pojo.GithubClient;
import yodgobekkomilov.edgar.com.githubapi.pojo.GithubRepo;
import yodgobekkomilov.edgar.com.githubapi.pojo.GithubService;


public class MainActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private GithubAdapter eAdapter;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle(R.string.toolbar_title);

        Intent intent = getIntent();
        String username = intent.getStringExtra("USER_NAME");

        //Creating an object of our api interface
        GithubService api = GithubClient.getApiService();

        /**
         * Calling JSON
         */


        Call<Github> call = api.getData(username);

        call.enqueue(new Callback<Github>() {
            @Override
            public void onResponse(Call<Github> call, Response<Github> response) {
                Github githubUser = response.body();


                TextView textView = findViewById(R.id.userName);
                textView.setText(githubUser.getLogin());

                ImageView avatarView = findViewById(R.id.avatar);

                Picasso.with(avatarView.getContext())
                        .load(githubUser.getAvatarUrl())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground).resize(250, 250).centerCrop().into(avatarView);


                TextView folTextView = findViewById(R.id.followers);
                folTextView.setText(String.valueOf(githubUser.getFollowers()));

                TextView repView = findViewById(R.id.repositories);
                repView.setText(String.valueOf(githubUser.getPublicRepos()));


            }

            @Override
            public void onFailure(Call<Github> call, Throwable t) {

            }
        });


        GithubService github = GithubClient.getApiService();
        final Call<GithubRepo[]> githubRepoCall = github.getRepos(username);
        githubRepoCall.enqueue(new Callback<GithubRepo[]>() {
            @Override
            public void onResponse(Call<GithubRepo[]> call, Response<GithubRepo[]> response) {
                GithubRepo[] repos = response.body();

                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

                eAdapter = new GithubAdapter(repos);
                RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(eLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(eAdapter);
            }

            @Override
            public void onFailure(Call<GithubRepo[]> call, Throwable t) {
              Log.i("Erroror", "Errororlar");
            }
        });


        /**
         * Enqueue Callback will be call when get response...
         */

//        call.enqueue(new Callback<GithubRepo>() {
//            @Override
//            public void onResponse(Call<GithubRepo> call, Response<GithubRepo> response) {
//                //Dismiss Dialog
//                pDialog.dismiss();
//
//                if (response.isSuccessful()) {
//                    /**
//                     * Got Successfully
//                     */
//                 GithubRepo   githubRepo = response.body();
//
//                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//                    GithubAdapter.GithubAdapterListener param2 = new GithubAdapter.GithubAdapterListener() {
//                        @Override
//                        public void onContactSelected(Github github) {
//
//                        }
//                    };
//                    eAdapter = new GithubAdapter(githubRepo, param2);
//                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
//                    recyclerView.setLayoutManager(eLayoutManager);
//                    recyclerView.setItemAnimator(new DefaultItemAnimator());
//                    recyclerView.setAdapter(eAdapter);
//                }
//            }
//
//
//
//            @Override
//            public void onFailure(Call<GithubRepo[]> call, Throwable t) {
//
//            }
//        });
//    }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}


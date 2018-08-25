package yodgobekkomilov.edgar.com.githubapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
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
    private Github githubArrayList;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private GithubAdapter eAdapter;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);


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

        call.enqueue(new Callback<Github>() {
            @Override
            public void onResponse(Call<Github> call, Response<Github> response) {
                githubArrayList = response.body();
                TextView textView = (TextView)findViewById(R.id.userName);
                textView.setText(githubArrayList.getName());
                ImageView avatarView = (ImageView)findViewById(R.id.avatar);
                avatarView.setTag(R.drawable.ic_launcher_background);
                TextView folTextView = (TextView)findViewById(R.id.followers);
                folTextView.setText(githubArrayList.getFollowers());

                TextView repView = (TextView)findViewById(R.id.repositories);
                folTextView.setText(githubArrayList.getReposUrl());



            }

            @Override
            public void onFailure(Call<Github> call, Throwable t) {

            }
        });

        /**
         * Enqueue Callback will be call when get response...
         */
//        call.enqueue(new Callback<Github>() {
//            @Override
//            public void onResponse(Call<Github> call, Response<Github> response) {
//                //Dismiss Dialog
//                pDialog.dismiss();
//
//                if (response.isSuccessful()) {
//                    /**
//                     * Got Successfully
//                     */
//                    githubArrayList = response.body();
//
//                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//                    GithubAdapter.GithubAdapterListener param2 = new GithubAdapter.GithubAdapterListener() {
//                        @Override
//                        public void onContactSelected(Github github) {
//
//                        }
//                    };
//                    eAdapter = new GithubAdapter(Collections.singletonList(githubArrayList), param2);
//                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
//                    recyclerView.setLayoutManager(eLayoutManager);
//                    recyclerView.setItemAnimator(new DefaultItemAnimator());
//                    recyclerView.setAdapter(eAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Github> call, Throwable t) {
//                pDialog.dismiss();
//            }
//        });
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

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }


    public void onContactSelected(Github github) {
        Toast.makeText(getApplicationContext(), "Selected: " + github.getName() + ", " + github.getAvatarUrl() + ", " + github.getFollowers() + ", " + github.getPublicRepos(), Toast.LENGTH_LONG).show();
    }
}


package yodgobekkomilov.edgar.com.githubapi.pojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubService {



    @GET("/users/kyodgorbek")
    Call<Github> getData();

    @GET("/users/kyodgorbek/repos")
    Call<GithubRepo[]> getRepos();



}

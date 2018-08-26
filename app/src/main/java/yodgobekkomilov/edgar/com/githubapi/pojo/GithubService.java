package yodgobekkomilov.edgar.com.githubapi.pojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {



    @GET("/users/{username}")
    Call<Github> getData(@Path("username") String username );

    @GET("/users/{username}/repos")
    Call<GithubRepo[]> getRepos(@Path("username") String username);



}

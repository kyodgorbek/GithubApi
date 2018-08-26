package yodgobekkomilov.edgar.com.githubapi.pojo;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Github extends GithubRepo {

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    @SerializedName("public_repos")
    @Expose
    private Integer publicRepos;

    @SerializedName("followers")
    @Expose
    private Integer followers;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(Integer publicRepos) {
        this.publicRepos = publicRepos;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }
}
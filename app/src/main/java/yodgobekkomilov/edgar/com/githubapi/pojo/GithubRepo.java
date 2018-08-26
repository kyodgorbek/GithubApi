
package yodgobekkomilov.edgar.com.githubapi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;


public class GithubRepo {


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("git_url")
    @Expose
    private String gitUrl;


    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("forks_count")
    @Expose
    private Integer forksCount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }


}


package com.gitsearch.githubsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubRepository {

    @JsonProperty("full_name")
    private String repoName;

    @JsonProperty("fork")
    private boolean isFork;
    @JsonProperty("branches_url")
    private String branchUrl;

    public String getRepoName() {
        return repoName;
    }

    public String getBranchUrl() {
        return branchUrl;
    }

    public boolean isFork() {
        return isFork;
    }



    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public void setFork(boolean fork) {
        isFork = fork;
    }
}

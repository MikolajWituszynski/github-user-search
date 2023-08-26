package com.gitsearch.githubsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubRepository {

    @JsonProperty("full_name")
    private String repoName;

    @JsonProperty("fork")
    private boolean isFork;

    public String getRepoName() {
        return repoName;
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

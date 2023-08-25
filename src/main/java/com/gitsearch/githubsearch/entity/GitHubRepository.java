package com.gitsearch.githubsearch.entity;

public class GitHubRepository {

    private String repoName;
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

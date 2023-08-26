package com.gitsearch.githubsearch.entity;

public class Branch {

    private String name;
    private String commitSHA;

    public String getName() {
        return name;
    }

    public String getCommitSHA() {
        return commitSHA;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommitSHA(String commitSHA) {
        this.commitSHA = commitSHA;
    }
}

package com.gitsearch.githubsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch {

    @JsonProperty("name")
    private String name;
    @JsonProperty("sha")
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

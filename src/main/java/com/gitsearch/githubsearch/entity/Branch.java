package com.gitsearch.githubsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch {

    @JsonProperty("name")
    private String name;
    @JsonProperty("sha")
    private String commitSHA;

    @JsonProperty("commit")
    private Commit commit;

    public String getName() {
        return name;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommitSHA(String commitSHA) {
        this.commitSHA = commitSHA;
    }
}

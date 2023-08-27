package com.gitsearch.githubsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Commit {
    @JsonProperty("sha")
    private String sha;

    public String getSha() {
        return sha;
    }
}

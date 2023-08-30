package com.gitsearch.githubsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GitHubSearchResponse {
    @JsonProperty("items")
    private List<GitHubRepository> items;

    public List<GitHubRepository> getItems() {
        return items;
    }

    public void setItems(List<GitHubRepository> items) {
        this.items = items;
    }
}

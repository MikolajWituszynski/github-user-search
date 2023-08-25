package com.gitsearch.githubsearch.Exceptions;

public class GitHubUserNotFoundException extends RuntimeException {

    public GitHubUserNotFoundException(String msg) {
        super(msg);
    }
}

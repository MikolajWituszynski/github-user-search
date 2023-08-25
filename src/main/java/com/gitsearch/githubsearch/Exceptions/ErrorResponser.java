package com.gitsearch.githubsearch.Exceptions;

public class ErrorResponser {
    private int status;
    private String message;

    public ErrorResponser(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

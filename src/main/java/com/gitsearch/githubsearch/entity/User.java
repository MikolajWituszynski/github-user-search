package com.gitsearch.githubsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Entity
public class User {

    @JsonProperty("login")
    private String username;

    public User( String username) {
        this.username = username;

    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
}

package com.gitsearch.githubsearch.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Entity
public class User {


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

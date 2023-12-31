package com.gitsearch.githubsearch.controller;

import com.gitsearch.githubsearch.Exceptions.ErrorResponser;
import com.gitsearch.githubsearch.Exceptions.GitHubUserNotFoundException;
import com.gitsearch.githubsearch.entity.GitHubRepository;
import com.gitsearch.githubsearch.service.GitHubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

@RestController
@RequestMapping("/api")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }


    @GetMapping(value = "/user/{username}/non-fork-repositories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Tuple3<String, String,String>>> getUserRepositoriesName(@PathVariable String username) {
        Flux<Tuple3<String, String,String>> repositories = gitHubService.getUsernameRepositoriesAndBranches(username);
        return ResponseEntity.ok(repositories);
    }

    @ExceptionHandler(GitHubUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponser> handleGitHubUserNotFoundException(GitHubUserNotFoundException ex) {
        ErrorResponser errorResponser = new ErrorResponser(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponser);
    }

    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponser> handleNotAcceptableHeader() {
        ErrorResponser errorResponser = new ErrorResponser(HttpStatus.NOT_ACCEPTABLE.value(), "Not acceptable header");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponser);
    }
}

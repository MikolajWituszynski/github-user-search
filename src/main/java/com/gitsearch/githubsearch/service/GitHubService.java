package com.gitsearch.githubsearch.service;

import com.gitsearch.githubsearch.entity.GitHubRepository;
import com.gitsearch.githubsearch.entity.GitHubSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class GitHubService {

    private final WebClient webClient;

    public GitHubService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Flux<GitHubRepository> getNonForkRepositories(String username) {
        return webClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(GitHubRepository.class);

    }

    public Flux<String> getUsernameRepositoriesNames(String username) {
        return webClient.get()
                .uri("/search/repositories?q=user{username}+fork:false",username)
                .retrieve()
                .bodyToMono(GitHubSearchResponse.class)
                .flatMapMany(response -> Flux.fromIterable(response.getItems()))
                .map(GitHubRepository::getRepoName);
    }
}

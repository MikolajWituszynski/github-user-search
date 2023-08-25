package com.gitsearch.githubsearch.service;

import com.gitsearch.githubsearch.entity.GitHubRepository;
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
                .uri("/users/{username}/repos?type=owner", username)
                .retrieve()
                .bodyToFlux(GitHubRepository.class)
                .filter(repo -> !repo.isFork());
    }
}

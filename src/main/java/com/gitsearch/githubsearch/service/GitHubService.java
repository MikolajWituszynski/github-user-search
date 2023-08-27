package com.gitsearch.githubsearch.service;

import com.gitsearch.githubsearch.entity.Branch;
import com.gitsearch.githubsearch.entity.GitHubRepository;
import com.gitsearch.githubsearch.entity.GitHubSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

@Service
public class GitHubService {

    private final WebClient webClient;

    public GitHubService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Flux<Tuple3<String, String,String>> getUsernameRepositoriesAndBranches(String username) {
        return webClient.get()
                .uri("/search/repositories?q=user:{username}+fork:false", username)
                .retrieve()
                .bodyToMono(GitHubSearchResponse.class)
                .flatMapMany(response -> Flux.fromIterable(response.getItems()))
                .flatMap(repository -> {
                    return webClient.get()
                            .uri("/repos/{username}/{repo}/branches", username, repository.getRepoName())
                            .retrieve()
                            .bodyToFlux(Branch.class)
                            .flatMap(branch -> {
                                return webClient.get()
                                        .uri("/repos/{username}/{repo}/branches", username, repository.getRepoName())
                                        .retrieve()
                                        .bodyToFlux(Branch.class)
                                        .map(commit -> Tuples.of(repository.getRepoName(), branch.getName(), branch.getCommitSHA()));
                            });
                });
    }

}

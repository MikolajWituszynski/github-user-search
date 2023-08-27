package com.gitsearch.githubsearch.service;

import com.gitsearch.githubsearch.entity.Branch;
import com.gitsearch.githubsearch.entity.GitHubSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GitHubService {

    private final WebClient webClient;

    public GitHubService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com")
                .defaultHeader("Authorization", "Bearer " + "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")
                .build();
    }

    public Flux<Tuple3<String, String, String>> getUsernameRepositoriesAndBranches(String username) {
        String encodedUsername;
        try {
            encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return Flux.error(e);
        }

        return webClient.get()
                .uri("/search/repositories?q=user:" + encodedUsername + "+fork:false")
                .retrieve()
                .bodyToMono(GitHubSearchResponse.class)
                .flatMapMany(response -> Flux.fromIterable(response.getItems()))
                .flatMap(repository -> {
                    String branchesUrl = repository.getBranchUrl();
                    String actualBranchesUrl = branchesUrl.replace("{/branch}", "");
                    System.out.println("Actual branch:" + actualBranchesUrl);
                    return webClient.get()
                            .uri(actualBranchesUrl)
                            .retrieve()
                            .bodyToFlux(Branch.class)
                            .flatMap(branch -> {
                                String repoName = repository.getRepoName();
                                String branchName = branch.getName();
                                String commitSHA = branch.getCommit().getSha();
                                if (repoName != null && branchName != null && commitSHA != null) {
                                    return Flux.just(Tuples.of(repoName, branchName, commitSHA));
                                } else {
                                    return Flux.just(Tuples.of(repoName, "null", "null"));

                                }
                            })
                            .onErrorMap(error -> {
                                System.err.println("Error fetching branches: " + error.getMessage());
                                return error;
                            });
                })
                .doOnError(error -> {
                    System.err.println("Error fetching repositories: " + error.getMessage());
                })
                .onErrorResume(throwable -> Flux.empty());
    }

}



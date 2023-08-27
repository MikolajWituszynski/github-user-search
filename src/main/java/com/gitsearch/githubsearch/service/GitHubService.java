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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GitHubService {

    private final WebClient webClient;

    public GitHubService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Flux<Tuple3<String, String, String>> getUsernameRepositoriesAndBranches(String username) {
        String encodedUsername;
        try {
            encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            // Handle encoding exception
            return Flux.error(e);
        }

        return webClient.get()
                .uri("/search/repositories?q=user:"+encodedUsername+"+fork:false")
                .retrieve()
                .bodyToMono(GitHubSearchResponse.class)
                .flatMapMany(response -> Flux.fromIterable(response.getItems()))
                .flatMap(repository -> {
                    // Repository name is not double-encoded here
                    String encodedRepoName = repository.getRepoName();

                    return webClient.get()
                            .uri("/repos/"+ encodedUsername+"/"+ encodedRepoName+"/branches")
                            .retrieve()
                            .bodyToFlux(Branch.class)
                            .flatMap(branch -> {
                                return Flux.just(Tuples.of(repository.getRepoName(), branch.getName(), branch.getCommitSHA()));
                            })
                            .doOnError(error -> {
                                System.err.println("Error fetching branches: " + error.getMessage());
                            })
                            .onErrorResume(throwable -> Flux.empty());
                })
                .doOnError(error -> {
                    System.err.println("Error fetching repositories: " + error.getMessage());
                })
                .onErrorResume(throwable -> Flux.empty());
    }


}



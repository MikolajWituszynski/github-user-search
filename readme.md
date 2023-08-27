GitHub Search Service Documentation
This service is responsible for interacting with the GitHub API to retrieve repositories and their branches for a given username.
GitHubService Class
Located in: com.gitsearch.githubsearch.service.GitHubService
@Service
public class GitHubService {

    // Constructor and WebClient initialization...

    /**
     * Retrieves repositories and their branches for a given username.
     *
     * @param username The username to search for.
     * @return A Flux of tuples containing repository name, branch name, and commit SHA.
     */
    public Flux<Tuple3<String, String, String>> getUsernameRepositoriesAndBranches(String username) {
        // Method implementation...
    }
}
GitHub Controller Documentation
This controller handles HTTP requests related to GitHub repository and branch information.

GitHubController Class
Located in: com.gitsearch.githubsearch.controller.GitHubController
@RestController
@RequestMapping("/api")
public class GitHubController {

    // Constructor injection of GitHubService...

    /**
     * Retrieves non-fork repositories and their branches for a given username.
     *
     * @param username The username to search for.
     * @return ResponseEntity containing a Flux of tuples with repository name, branch name, and commit SHA.
     */
    @GetMapping(value = "/user/{username}/non-fork-repositories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Tuple3<String, String, String>>> getUserRepositoriesName(@PathVariable String username) {
        // Method implementation...
    }

    /**
     * Exception handler for GitHubUserNotFoundException.
     *
     * @param ex The exception to handle.
     * @return ResponseEntity with an error response.
     */
    @ExceptionHandler(GitHubUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponser> handleGitHubUserNotFoundException(GitHubUserNotFoundException ex) {
        // Method implementation...
    }

    /**
     * Exception handler for handling not acceptable header.
     *
     * @return ResponseEntity with an error response.
     */
    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponser> handleNotAcceptableHeader() {
        // Method implementation...
    }
}


To setup github access token as environment variable on windows:
Windows:

Open the Start menu and search for "Environment Variables" or "Edit the system environment variables".

Click on "Edit the system environment variables" to open the System Properties window.

In the System Properties window, click the "Environment Variables" button.

In the Environment Variables window, under the "System variables" section, click the "New" button.

Enter the name of the variable (e.g., GITHUB_ACCESS_TOKEN) and its value (your GitHub access token).

Click "OK" to save the environment variable.

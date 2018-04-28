package aiss.model.resource;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.model.github.InviteCollaboratorResult;
import aiss.model.github.RepositoryCreateResult;

/**
 * Class providing interaction with the GitHub API.
 *
 *
 * @author José María Guisado Gómez
 *
 */
public class GithubResource {

	private static final Logger log = Logger.getLogger(GithubResource.class.getName());
	private static final String GITHUB_JSON_PATH = "WEB-INF/credentials/github.json";
	private static final String ENDPOINT = "https://api.github.com";
	// TODO: Add header for explicit version v3 usage
	private static final String EXPLICIT_V3_HEADER = "Accept: application/vnd.github.v3+json";

	private static String GITHUB_TOKEN;
	private static String GITHUB_OWNER;

	/**
	 * Creates a GithubResource object and sets the token property reading it from
	 * the credential json.
	 */
	public GithubResource() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;

		try {
			root = mapper.readTree(new File(GITHUB_JSON_PATH));
			GITHUB_TOKEN = root.get("token").asText();
			GITHUB_OWNER = root.get("owner").asText();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Error processing json for GitHub credentials", e);
			e.printStackTrace();
		}
	}

	/**
	 * Sends POST request to create a repository for the Kiwi GitHub's account with
	 * a given name.
	 *
	 * @author José María Guisado Gómez
	 * @param name
	 *            Name of the repository to be created
	 *
	 * @throws NullPointerException when name is null
	 * 
	 * @return Returns a POJO with the representation of the response body
	 */
	public RepositoryCreateResult createRepository(String name) {
		if(name == null) {
			throw new NullPointerException("Name string of the new repository can't be null");
		}

		// Response's body pojo
		RepositoryCreateResult res = null;

		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(ENDPOINT + "/user/repos");

			// Authorization
			request.setHeader("Authorization", "token " + GITHUB_TOKEN);

			// Request body
			StringEntity se = new StringEntity("{ \"name\":\"" + name + "\", \"auto_init\": true }");
			request.setEntity(se);

			// Send request and get the response (headers and body)
			HttpResponse resp = client.execute(request);
			log.log(Level.INFO, "POST sent to create a new kiwi repository with name " + name);
			// Extract the body from the response
			String jsonResponseBody = IOUtils.toString(resp.getEntity().getContent());

			// Deserialize the JSON into the POJO class
			ObjectMapper mapper = new ObjectMapper();
			res = mapper.readValue(jsonResponseBody, RepositoryCreateResult.class);

		} catch (IOException e) {
			log.log(Level.SEVERE, "Error while trying to create a new repository with name" + name, e);
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Sends PUT request to invite a GitHub user as collaborator to the repository
	 * given both as parameters
	 * 
	 * @param repoName
	 *            The name of the repository, owned by the kiwi GitHub account set
	 *            in credentials
	 * @param username
	 *            GitHub invitee account username
	 * @return POJO representing the JSON response from GitHub
	 */
	public InviteCollaboratorResult inviteCollaborator(String repoName, String username) {

		InviteCollaboratorResult res = null;

		try {

			HttpClient client = new DefaultHttpClient();
			HttpPut request = new HttpPut(
					ENDPOINT + "/" + String.join("/", "repos", GITHUB_OWNER, repoName, "collaborators", username));

			// Authorization
			request.setHeader("Authorization", "token " + GITHUB_TOKEN);

			// Set Content-Length to 0 as this is specified by GitHub API
			// documentation when not passing parameters
			// request.setHeader("Content-Length", "0");

			// Send request and get the response (headers and body)
			HttpResponse resp = client.execute(request);

			log.log(Level.INFO, "PUT sent to invite user " + username + " to repository " + repoName);
			// Extract the body from the response
			String jsonResponseBody = IOUtils.toString(resp.getEntity().getContent());

			// Deserialize the JSON into the POJO class
			ObjectMapper mapper = new ObjectMapper();
			res = mapper.readValue(jsonResponseBody, InviteCollaboratorResult.class);

		} catch (IOException e) {
			log.log(Level.SEVERE,
					"Error while trying to invite user \"" + username + "\" to repository with name: " + repoName, e);
			e.printStackTrace();
		}

		return res;
	}

}

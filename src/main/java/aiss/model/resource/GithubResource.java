package aiss.model.resource;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	private static final String GITHUB_JSON_PATH = "src/main/webapp/credentials/github.json";
	private static final String ENDPOINT = "https://api.github.com";
	// TODO: Add header for explicit version v3 usage
	private static final String EXPLICIT_V3_HEADER = "Accept: application/vnd.github.v3+json";
	
	private static String GITHUB_TOKEN;
	
	/**
	 * Creates a GithubResource object and sets
	 * the token property reading it from the
	 * credential json.
	 */
	public GithubResource() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		
		try {
			root = mapper.readTree(new File(GITHUB_JSON_PATH));
			GITHUB_TOKEN = root.get("token").asText();
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
	 * @return Returns a POJO with the representation of the response body
	 */
	public RepositoryCreateResult createRepository(String name) {
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

}

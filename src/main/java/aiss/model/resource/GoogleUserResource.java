package aiss.model.resource;

import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.google.GoogleUser;

public class GoogleUserResource {
	private static final Logger log = Logger.getLogger(GoogleUserResource.class.getName());

	private String uri = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=";
	private String accessToken;

	public GoogleUserResource(String accessToken) {
		this.accessToken = accessToken;
	}

	public GoogleUser getLoggedUser() {
		ClientResource cr = null;
		GoogleUser user = null;
		try {
			cr = new ClientResource(uri + accessToken);
			user = cr.get(GoogleUser.class);
		} catch (ResourceException e) {
			log.warning("Error when retrieving user info: " + cr.getResponse().getStatus());
		}
		return user;
	}
}

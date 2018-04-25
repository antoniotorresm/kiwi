package aiss.model.resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;

public class GoogleCalendarResource {
	private static final Logger log = Logger.getLogger(GoogleCalendarResource.class.getName());

	// AppEngine data store. One singleton across the entire application
	private static final AppEngineDataStoreFactory DATA_STORE_FACTORY = AppEngineDataStoreFactory.getDefaultInstance();
	// HTTPTransport singleton
	private static final HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();
	// JSON Factory
	private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	// Google service account credentials
	private static GoogleClientSecrets clientSecrets = null;

	public GoogleClientSecrets getClientCredential() {
		if (clientSecrets == null) {
			InputStreamReader secretsFile = new InputStreamReader(
					GoogleCalendarResource.class.getResourceAsStream("credentials/google_client_secrets.json"));
			try {
				clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, secretsFile);
				Preconditions.checkArgument(
						!clientSecrets.getDetails().getClientId().startsWith("Enter ")
								&& !clientSecrets.getDetails().getClientSecret().startsWith("Enter "),
						"Google API Credentials not found, make sure to place them in credentials/google_client_secrets.json");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clientSecrets;
	}

}

package aiss.model.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

/**
 * 
 * @author atorresm
 *
 */
public class GoogleCalendarResource {
	private static final Logger log = Logger.getLogger(GoogleCalendarResource.class.getName());

	// HTTPTransport singleton
	private static final HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();
	// JSON Factory
	private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	// Google Calendar API caller
	Calendar calendarAdmin = null;

	public GoogleCalendarResource() {
		try {
			GoogleCredential credential = GoogleCredential
					.fromStream(new FileInputStream("credentials/google_client_secrets.json"))
					.createScoped(Collections.singleton(CalendarScopes.CALENDAR));
			calendarAdmin = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("Kiwi")
					.build();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public com.google.api.services.calendar.model.Calendar getPrimaryCalendar() throws IOException {
		log.log(Level.FINE, "Primary calendar retrieved");
		return calendarAdmin.calendars().get("primary").execute();
	}

}

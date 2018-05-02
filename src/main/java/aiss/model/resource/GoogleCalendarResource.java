package aiss.model.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Event.ExtendedProperties;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;

/**
 * 
 * @author Antonio Torres Moríñigo
 *
 */
public class GoogleCalendarResource {
	private static final Logger log = Logger.getLogger(GoogleCalendarResource.class.getName());
	private static final String GOOGLE_JSON_PATH = "WEB-INF/credentials/google_client_secrets.json";

	// HTTPTransport singleton
	private static final HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();
	// JSON Factory
	private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	// Google Calendar API caller
	Calendar calendarAdmin = null;

	public GoogleCalendarResource() {
		try {
			GoogleCredential credential = GoogleCredential
					.fromStream(new FileInputStream(GOOGLE_JSON_PATH))
					.createScoped(Collections.singleton(CalendarScopes.CALENDAR));
			calendarAdmin = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("Kiwi")
					.build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public com.google.api.services.calendar.model.Calendar getPrimaryCalendar() throws IOException {
		log.log(Level.FINE, "Primary calendar retrieved");
		return calendarAdmin.calendars().get("primary").execute();
	}

	public String createEvent(String name, String description, String location, LocalDateTime fechaInicio,
			LocalDateTime fechaFin, String creatorEmail) throws IOException {
		Event event = new Event().setSummary(name).setLocation(location).setDescription(description);
		EventDateTime startDateTime = convertLocalDateTimeToEventDateTime(fechaInicio);
		EventDateTime endDateTime = convertLocalDateTimeToEventDateTime(fechaFin);
		event.setStart(startDateTime);
		event.setEnd(endDateTime);
		event.setAnyoneCanAddSelf(true);
		event.setVisibility("public");
		// Creator
		EventAttendee creator = new EventAttendee().setEmail(creatorEmail);
		creator.setOrganizer(true);
		event.setAttendees(Arrays.asList(creator));
		Event created = calendarAdmin.events().insert("primary", event).setSendNotifications(true).execute();
		log.log(Level.FINE, "Event created");
		return created.getId();
	}

	private EventDateTime convertLocalDateTimeToEventDateTime(LocalDateTime in) {
		ZoneOffset offset = ZoneId.of("Europe/Madrid").getRules().getOffset(Instant.now());
		long millisSinceEpoch = in.toEpochSecond(offset) * 1000;
		EventDateTime res = new EventDateTime().setDateTime(new DateTime(millisSinceEpoch))
				.setTimeZone("Europe/Madrid");
		return res;
	}

	public void saveEventData(String eventId, String repoUrl, String hashtag) throws IOException {
		ExtendedProperties data = new ExtendedProperties();
		Map<String, String> map = new HashMap<>();
		map.put("repoUrl", repoUrl);
		map.put("hashtag", hashtag);
		data.setShared(map);
		Event changes = new Event().setExtendedProperties(data);
		Event patched = calendarAdmin.events().patch("primary", eventId, changes).execute();
		log.log(Level.FINE, "Repo URL saved: " + patched.getExtendedProperties().getShared().get("repoUrl"));
		log.log(Level.FINE, "Hashtag saved: " + patched.getExtendedProperties().getShared().get("hashtag"));
	}
	
	public Event getEvent(String eventId) throws IOException {
		return calendarAdmin.events().get("primary", eventId).execute();
	}

	public String getEventHashtag(String eventId) throws IOException {
		return calendarAdmin.events().get("primary", eventId).execute().getExtendedProperties().getShared()
				.get("hashtag");
	}

	public String getEventRepoUrl(String eventId) throws IOException {
		return calendarAdmin.events().get("primary", eventId).execute().getExtendedProperties().getShared()
				.get("repoUrl");
	}

	public Integer eventAttendeesNumber(String eventId) throws IOException {
		Event event = calendarAdmin.events().get("primary", eventId).execute();
		if (event.getAttendees() != null) {
			return event.getAttendees().size();
		} else {
			return 0;
		}
	}

	public com.google.api.services.calendar.model.Events getEvents() throws IOException {
		return calendarAdmin.events().list("primary").execute();
	}

	public void sendInvitationToUser(String email, String eventId) throws IOException {
		Event event = calendarAdmin.events().get("primary", eventId).execute();
		List<EventAttendee> attendees = event.getAttendees();
		if (attendees == null) {
			attendees = new ArrayList<EventAttendee>();
		}
		attendees.add(new EventAttendee().setEmail(email));
		Event changes = new Event().setAttendees(attendees);
		calendarAdmin.events().patch("primary", eventId, changes).setSendNotifications(true).execute();
		log.log(Level.FINE, "Invitation sent to user: " + email);
	}

}

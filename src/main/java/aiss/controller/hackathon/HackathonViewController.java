package aiss.controller.hackathon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.calendar.model.Event;

import aiss.model.resource.GoogleCalendarResource;
import aiss.model.resource.TwitterResource;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Servlet implementation class GoogleCalendarController
 */
public class HackathonViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HackathonViewController.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HackathonViewController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Load Google Calendar information
		GoogleCalendarResource gcr = new GoogleCalendarResource();
		String eventId = (String) request.getAttribute("eventId");
		log.log(Level.FINE, "Id=" + eventId);
		Event event = null;
		String hashtag = null;
		String eventRepoUrl = null;
		if (eventId != null) {
			event = gcr.getEvent(eventId);
			hashtag = gcr.getEventHashtag(eventId);
			eventRepoUrl = gcr.getEventRepoUrl(eventId);
		}
		com.google.api.services.calendar.model.Calendar calendar = gcr.getPrimaryCalendar();

		if (hashtag == null) {
			request.setAttribute("message", "Event not found");
			log.log(Level.FINE, "Event not found.");
		} else
			log.log(Level.FINE, "Event with id " + eventId + " loaded.");

		// Load Twitter Information
		List<Status> listTweets = new ArrayList<>();
		try {
			TwitterResource tweet = new TwitterResource();
			listTweets.addAll(tweet.query(hashtag));
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		// Forward to event view
		request.setAttribute("listTweets", listTweets);
		request.setAttribute("event", event);
		request.setAttribute("hashtag", hashtag);
		request.setAttribute("eventRepoUrl", eventRepoUrl);
		request.setAttribute("calendar", calendar);
		request.getRequestDispatcher("/EventDetailView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package aiss;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.GoogleCalendarResource;

public class HelloWorldServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HelloWorldServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// Sample log
		log.log(Level.FINE, "Processing GET request");

		resp.setContentType("text/plain");
		resp.getWriter().println("Hello world!");

		// Google Calendar API Test
		GoogleCalendarResource resource = new GoogleCalendarResource();
		resp.getWriter().println(resource.getPrimaryCalendar().getSummary());
		com.google.api.services.calendar.model.Events events = resource.getEvents();
		resource.sendInvitationToUser("torres.morinigo.antonio@gmail.com", events.getItems().get(1).getId());
	}
}

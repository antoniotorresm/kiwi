package aiss.controller.hackathon;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.calendar.model.Event;

import aiss.model.resource.GoogleCalendarResource;

/**
 * Servlet implementation class HackathonListViewController
 */
public class HackathonListViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HackathonViewController.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HackathonListViewController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Load all events
		GoogleCalendarResource gcr = new GoogleCalendarResource();
		List<Event> events = gcr.getEvents().getItems();

		if (events.isEmpty()) {
			request.setAttribute("message", "There're no events.");
			log.log(Level.FINE, "There're no events.");
		} else
			log.log(Level.FINE, "Events loaded.");

		// Forward to events view
		request.setAttribute("events", events); // events -> similar to map
		request.getRequestDispatcher("/EventListView.jsp").forward(request, response);
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

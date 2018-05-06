package aiss.controller.hackathon;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.GoogleCalendarResource;
import aiss.model.resource.GoogleUserResource;

/**
 * Servlet implementation class HackathonJoinController
 */
public class HackathonJoinController extends HttpServlet {
	private static final Logger log = Logger.getLogger(HackathonJoinController.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HackathonJoinController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GoogleCalendarResource gc = new GoogleCalendarResource();
		String accessToken = (String) request.getSession().getAttribute("Google-token");
		if (accessToken != null && !"".equals(accessToken)) {
			GoogleUserResource gu = new GoogleUserResource(accessToken);
			gc.sendInvitationToUser(gu.getLoggedUser().getEmail(), (String) request.getAttribute("eventId"));
			log.fine("User " + gu.getLoggedUser().getEmail() + " invited to event with id " + request.getAttribute("eventId"));
		} else {
			// User not logged in
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package aiss;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.GoogleUser;
import aiss.model.resource.GoogleUserResource;

public class HelloWorldServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HelloWorldServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Sample log
		log.log(Level.FINE, "Processing GET request");

		resp.setContentType("text/plain");
		resp.getWriter().println("Hello world!");

		// Google Calendar API Test
		// GoogleCalendarResource resource = new GoogleCalendarResource();
		// resp.getWriter().println(resource.getPrimaryCalendar().getSummary());
		// Twitter test
		// TwitterResource twitter;
		// try {
		// twitter = new TwitterResource();
		// List<Status> tweets = twitter.query("#test");
		// for (Status status : tweets.subList(0, 10)) {
		// resp.getWriter().println(status.getUser().getName() + ": " +
		// status.getText());
		// }
		// } catch (TwitterException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Google User API test
		String accessToken = (String) req.getSession().getAttribute("Google-token");
		if (accessToken != null && !"".equals(accessToken)) {
			log.info("Access token available");
			// access token available
			resp.getWriter().println("Access token: " + accessToken);
			GoogleUserResource ur = new GoogleUserResource(accessToken);
			GoogleUser user = ur.getLoggedUser();
			resp.getWriter().println("Current logged in user email: " + user.getEmail());
		} else {
			// no access token, redirect to oauth servlet
			log.info("Trying to access Google without access token");
			req.getRequestDispatcher("/AuthController/Google").forward(req, resp);
		}
	}
}

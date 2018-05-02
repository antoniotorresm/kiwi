package aiss;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TwitterResource;
import twitter4j.Status;
import twitter4j.TwitterException;

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
		//GoogleCalendarResource resource = new GoogleCalendarResource();
		//resp.getWriter().println(resource.getPrimaryCalendar().getSummary());
		// Twitter test
		TwitterResource twitter;
		try {
			twitter = new TwitterResource();
			List<Status> tweets = twitter.query("#test");
			for (Status status : tweets.subList(0, 10)) {
				resp.getWriter().println(status.getUser().getName() + ": " + status.getText());
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

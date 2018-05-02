package aiss.model.resource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterResource {
	private static Twitter twitter;
	private static final Logger log = Logger.getLogger(TwitterResource.class.getName());
	private static final String TWITTER_JSON_PATH = "WEB-INF/credentials/twitter.json";
	private static String CONSUMER_SECRET;
	private static String CONSUMER_KEY;
	private static String ACCESS_TOKEN;
	private static String ACCESS_TOKEN_SECRET;

	public TwitterResource() throws TwitterException {
		// Parse credentials json
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(TWITTER_JSON_PATH));
			CONSUMER_SECRET = root.get("consumer_secret").asText();
			CONSUMER_KEY = root.get("consumer_key").asText();
			ACCESS_TOKEN = root.get("access_token").asText();
			ACCESS_TOKEN_SECRET = root.get("access_token_secret").asText();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Error processing json for Twitter credentials", e);
			e.printStackTrace();
		}
		// Build Twitter instance
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerSecret(CONSUMER_SECRET).setOAuthConsumerKey(CONSUMER_KEY).setOAuthAccessToken(ACCESS_TOKEN)
				.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}

	public List<Status> query(String query) throws TwitterException {
		QueryResult search = twitter.search(new Query(query));
		List<Status> tweets = search.getTweets();
		return tweets;
	}

	public void printStatus(Status status) {
		System.out.println("************************************************");
		System.out.println(String.format(status.getUser().getScreenName()));
		System.out.println(status.getText());
		System.out.println(String.format("RT: " + status.getRetweetCount() + " FAV: " + status.getFavoriteCount()));
	}

	public void printStatus(List<Status> status) {
		for (Status tweet : status) {
			printStatus(tweet);
		}
	}
}
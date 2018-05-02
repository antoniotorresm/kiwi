package aiss.model.twitter;

import java.io.IOException;
import java.util.List;

import aiss.model.resource.TwitterResource;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterSearch {

	public static void main(String[] args) throws IOException, TwitterException {
		
		TwitterResource tr = new TwitterResource();
		String search = QueryReader.readLine("Valor de Busqueda: ");
		
		while (!search.equals("trdone")) {
			List<Status> result = tr.query(search);
			tr.printStatus(result);
		}
	}

}
import java.io.IOException;
import java.io.InputStreamReader;


import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
import twitter4j.auth.AccessToken;


public class TwitterSearch {
	private final static String CONSUMER_KEY = "NGi9eQkpkbI0Kugcc58TRG7Tq";
	private final static String CONSUMER_KEY_SECRET = "Xp6Us87bJn8NjrhMBF8eYJN5TZFrqCVSh3VZNeQTTjNE46lQQv";
	private Twitter twitter;
	
	public TwitterSearch() {
		this.twitter = acctSetup();
	}
	
	public Twitter acctSetup() {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);

		//***Set Account tokens***
		String accessToken = getSavedAccessToken();
		String accessTokenSecret = getSavedAccessTokenSecret();
		AccessToken oathAccessToken = new AccessToken(accessToken,
				accessTokenSecret);

		twitter.setOAuthAccessToken(oathAccessToken);
		//***End setting Account tokens***

		return twitter;

	}

	private String getSavedAccessTokenSecret() {
		return "Dyt4pkFoSuHyQvQP72GkAxd3ylhJROAUxUFokDb62LfmT";
	}

	private String getSavedAccessToken() {
		return "49802517-ra1MLOyESv0I0qol8LPJx1mOTc5vk5XYJuzzgVUg9";
	}


	public ArrayList<QueryResult> query(String query) {
		ArrayList<QueryResult> queryResults = new ArrayList<QueryResult>();
		QueryResult result;
		
		try {
			Query qry = new Query(query);
			//do {
				result = twitter.search(qry); 
				queryResults.add(result);
			//} while ((qry = result.nextQuery()) != null);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
		return queryResults;
	}
	
	public void printQueryTweets(ArrayList<QueryResult> queryResults) {
		for(int i=0; i<queryResults.size(); i++) {
			QueryResult result = queryResults.get(i);
			List<Status> tweets = result.getTweets();
			for (Status tweet : tweets) {
				System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
			}
		}
	}
}

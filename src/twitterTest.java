import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import twitter4j.QueryResult;


public class twitterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TwitterSearch TS = new TwitterSearch();
		ArrayList<String> queryList = null;
		
		//ArrayList<QueryResult> queryResults = TS.query(args[0]);
		//TS.printQueryTweets(queryResults);
		
		try {
			queryList = QueryList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0; i<queryList.size(); i++) {
			ArrayList<QueryResult> queryResults = TS.query(queryList.get(i));
			TS.printQueryTweets(queryResults);
		}
	}
	
	public static ArrayList<String> QueryList() throws IOException {
		ArrayList<String> queryList = new ArrayList<String>();
		InputStream    fis;
		BufferedReader br;
		String         line;
		
		fis = new FileInputStream("twitter.txt");
		br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
		while ((line = br.readLine()) != null) {
			queryList.add(line);
		}
		br.close();
		return queryList;
	}

}

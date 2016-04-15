package lesson3.assign;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * 
 * json format : https://goshippo.com/shipping-api/tracking-standalone
 * 
 */

public class APITracking {
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	public static String getTrackingSts(String uspsnumber) throws Exception{
		String status = "";
		
		try {
			String rootUrl = "https://api.goshippo.com/v1/tracks/usps/";
		    JSONObject json = new JSONObject(readUrl(rootUrl+uspsnumber));

		    JSONObject tracking_status = (JSONObject)json.get("tracking_status");
		    status = (String)tracking_status.get("status");
		//    status = (String)tracking_status.get("status");
		   

		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
		return status;
	}
	
}

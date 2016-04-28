package lesson3.assign;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
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
			
			Package my_package = new Package();
			String rootUrl = "https://api.goshippo.com/v1/tracks/usps/";
		    JSONObject json = new JSONObject(readUrl(rootUrl+uspsnumber));

		    ////////////////////////////////////////////////////////////////////////////////////////////////
		    my_package.tracking_number = (String)json.get("tracking_number");
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////
		    my_package.carrier = (String)json.get("carrier");
            
		    ////////////////////////////////////////////////////////////////////////////////////////////////
		    JSONObject tracking_status = (JSONObject)json.get("tracking_status");
		    
		    my_package.tracking_status.object_created = (String)tracking_status.get("object_created");
		    my_package.tracking_status.status = (String)tracking_status.get("status");
		    status = my_package.tracking_status.status;
		    my_package.tracking_status.status_details = (String)tracking_status.get("status_details");
		    my_package.tracking_status.status_date = (String)tracking_status.get("status_date");
		    
		    JSONObject location = (JSONObject)tracking_status.get("location");
		    my_package.tracking_status.location.city = (String)location.get("city");
		    my_package.tracking_status.location.state = (String)location.get("state");
		    my_package.tracking_status.location.zip = (String)location.get("zip");
		    my_package.tracking_status.location.country = (String)location.get("country");
		    
		    ///////////////////////////////////////////////////////////////////////////////////////////////////////
		    JSONArray tracking_history = (JSONArray)json.get("tracking_history");
		    for ( int index = 0; index < tracking_history.length();  index++ ) {
		    	
		    	JSONObject status_index = tracking_history.getJSONObject(index);
		    	Tracking_status ts = new Tracking_status();
			    
		    	ts.object_created = (String)status_index.get("object_created");
			    ts.status = (String)tracking_status.get("status");
			    ts.status_details = (String)tracking_status.get("status_details");
			    ts.status_date = (String)tracking_status.get("status_date");
			    
		    	my_package.tracking_history.add(ts);

		    }
		//    for (JSONObject job : tracking_history) {
		    	
		  //  }
		    System.out.println("assfadfasdf");

		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
		return status;
	}
	
}

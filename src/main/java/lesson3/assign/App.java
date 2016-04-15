package lesson3.assign;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
    	   System.out.println( "Hello World!" );	
    	String usps_number = "9200199999977453249942";
    	String status_jsoup = JsoupTracking.getTrackingSts(usps_number);
        System.out.println( status_jsoup );
    	String status_api = APITracking.getTrackingSts(usps_number);
     
  
        System.out.println( status_api );
        
    }
}


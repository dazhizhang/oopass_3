package lesson3.assign;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ) throws Exception {
    	   
    	System.out.println( "Hello World!" );	
    	
    	 Injector injector = Guice.createInjector(new NotOnWeekendsModule());
    	 APITracking hero = injector.getInstance(APITracking.class);
    	 hero.testnotoneweekeds();
    
    	
    	String usps_number = "9200199999977453249942";
    	String status_api = hero.getTrackingSts(usps_number);
   	     System.out.println( status_api );
   	     
 //   	String status_jsoup = JsoupTracking.getTrackingSts(usps_number);
  //      System.out.println( status_jsoup );

        
    }
}


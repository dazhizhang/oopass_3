package lesson3.assign;

import java.io.IOException;
import java.util.regex.Pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws Exception 
     */
    public void testApp() throws Exception
    {
    	String usps_number = "9200199999977453249942";
    	assertTrue(usps_number.length() == 22);
    	assertTrue(isNumeric(usps_number));
    	testOneUsps(usps_number);
        
    	usps_number = "9400110200881976430106";
    	assertTrue(isNumeric(usps_number));
    	assertTrue(usps_number.length() == 22);
    	testOneUsps(usps_number);
    }
    
    public void testOneUsps(String usps_number) throws Exception {
    	String status_jsoup = JsoupTracking.getTrackingSts(usps_number);
        System.out.println( status_jsoup );
    	String status_api = APITracking.getTrackingSts(usps_number);
    	System.out.println( status_api );
        assertTrue( status_jsoup.equalsIgnoreCase(status_api) );
        
    }
    
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();   
    }
    
}

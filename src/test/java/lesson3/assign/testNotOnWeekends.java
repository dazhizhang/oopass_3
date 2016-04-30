package lesson3.assign;

import java.util.regex.Pattern;

import com.google.inject.Guice;
import com.google.inject.Injector;

import junit.framework.TestCase;

public class testNotOnWeekends  extends TestCase{
    public void testApp() throws Exception
    {
		   	 Injector injector = Guice.createInjector(new NotOnWeekendsModule());
		   	 APITracking hero = injector.getInstance(APITracking.class);
		   	hero.testnotoneweekeds() ;
   
    }
    
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();   
    }
}

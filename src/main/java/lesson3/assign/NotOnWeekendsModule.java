package lesson3.assign;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import lesson3.assign.NotOnWeekends;

public class NotOnWeekendsModule extends AbstractModule {
	  protected void configure() {
	    bindInterceptor(Matchers.any(), Matchers.annotatedWith(NotOnWeekends.class), 
	        new WeekendBlocker());
	  }
	}
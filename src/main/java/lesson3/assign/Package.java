package lesson3.assign;

import java.util.ArrayList;

class Location {
	public String city;
	public String state;
	public String zip;
	public String country;
}

class Tracking_status {
	
	public String object_created;
	public String status;
	public String status_details;
	public String status_date;
	
	public Location location = new Location();
	
}

public class Package {
	
	public String tracking_number;
	public String carrier;
	public Tracking_status tracking_status = new Tracking_status();
	public ArrayList<Tracking_status> tracking_history = new ArrayList<Tracking_status>();	
	
}

package lesson3.assign;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTracking {

	
	private static Package getPackage_info(Document doc) {
		
		Package my_package = new Package();
		try{
			// tracking_number 
			Elements element_tracking_number = doc.getElementsByClass("tracking-number");
	//		Element t_number = element_tracking_number.get(0) .tagName("span");
	//		String tracking_number = element_tracking_number.text();
			my_package.tracking_number = element_tracking_number.text().replaceAll("Tracking Number:", "").trim(); 
			
			// carrier 
			Elements element_carrier = doc.getElementsByClass("feature");
	//		Element t_number = element_tracking_number.get(0) .tagName("span");
	//		String tracking_number = element_tracking_number.text();
			my_package.carrier = element_carrier.text(); 
			
			Elements  elements_h2 = doc.getElementsByTag("h2");
			
			for (Element h_2 : elements_h2) {
				  String h_2_Text = h_2.outerHtml();
			//	  System.out.println(h_2_Text);
				  if (h_2_Text.contains("<h2 class=\"hide-fromsighted\">")) {
					String  status = h_2.text();
					  my_package.tracking_status.status = status;
					  break;
				  }
			}
			
			Elements  elements_detail = doc.getElementsByTag("td");
			my_package.tracking_status.status_date = elements_detail.get(0).text(); 
			
			Elements elements_status_detail = doc.getElementsByClass("tracking-summary-details");
			my_package.tracking_status.status_details = elements_status_detail.get(0).text(); 
			
			Elements elements_status_location = doc.getElementsByClass("location");
			String location = elements_status_location.get(1).text(); 
			String[] location_s = location.split(",");
			my_package.tracking_status.location.city = location_s[0];
			my_package.tracking_status.location.state = location_s[1].substring(0, 3);
			my_package.tracking_status.location.zip = location_s[1].substring(4,  location_s[1].length());
			
			Elements elements_history = doc.getElementsByTag("tr");
			boolean firstline = true;
			for (int s = 0; s< elements_history.size(); s++) {
				System.out.println(elements_history.get(s).toString());
				if (    !(elements_history.get(s).toString().contains("date-time") )  ) {
					continue;
				}
				if (firstline) {
					firstline = false;
					continue;
					
				}
				Tracking_status ts = new Tracking_status();
				String[] element_times = new String[2] ;
				if (elements_history.get(s).text().contains(" am ")) {
					element_times = elements_history.get(s).text().split(" am ");
					ts.status_date = element_times[0] + " am ";
				}
				else if (elements_history.get(s).text().contains(" pm ")) {
					element_times = elements_history.get(s).text().split(" pm ");
					ts.status_date = element_times[0] + " pm ";
				}
				
				if ( elements_history.get(s).text().contains(" pm ") || elements_history.get(s).text().contains(" am ")  ) {
						String[] element_status = element_times[1].split(" ");
						ts.status = "";
						for (int st = 0; st < element_status.length-2; st ++) {
							ts.status = ts.status + " " +element_status[st]; 
						}
						location_s = element_status[element_status.length-1].split(",");
						ts.location.city = location_s[0];
						if (location_s.length == 2) {
							ts.location.state = location_s[1].substring(0, 3);
							ts.location.zip = location_s[1].substring(4,  location_s[1].length());
						}
				}
				my_package.tracking_history.add(ts);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return my_package;
	}
	
	public static String getTrackingSts(String uspsnumber) throws IOException, InterruptedException {
		
		String status = "";
		int repeat_times = 0;
		while(true) {
			try {
				
				String urlroot = "https://tools.usps.com/go/TrackConfirmAction?qtc_tLabels1=";
				String url = urlroot + uspsnumber;
				
				Document doc = Jsoup.connect(url)
						.data("query", "Java")
						  .userAgent("Mozilla")
						  .cookie("auth", "token")
						  .timeout(3000).
						get();
	            
				Package my_package = getPackage_info(doc);
				status = my_package.tracking_status.status;
				return status;
				
			} catch (Exception e) {
				Thread.sleep(1000);
				repeat_times++;
				if (repeat_times>=3) {
					break;
				}
			}
		}
		
		return status;
		
	}
	
}

package lesson3.assign;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTracking {
	
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
			
			Elements  elements_h2 = doc.getElementsByTag("h2");
			
			for (Element h_2 : elements_h2) {
				  String h_2_Text = h_2.outerHtml();
			//	  System.out.println(h_2_Text);
				  if (h_2_Text.contains("<h2 class=\"hide-fromsighted\">")) {
					  status = h_2.text();
					  break;
				  }
			}
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

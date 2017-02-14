package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class URLReader {
	private String url;
	
	public URLReader() {
		url = null;
	}
	
	public void setWeatherURL(String city) {
		url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=9ed81d9300f326bbd3f1ef06bb0f1207";
	}
	
	public void setPageURL(String url) {
		this.url = url;
	}
	
	public String getResponse() throws Exception {
		URL weatherUrl = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(weatherUrl.openStream()));
		return bufferedReaderToString(in);
	}

	private static String bufferedReaderToString(BufferedReader in) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}

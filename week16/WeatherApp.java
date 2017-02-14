package week16;

import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class WeatherApp {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		
		URLReader urlReader = new URLReader();
		urlReader.setWeatherURL(userInput);
		
		String weatherAppInfo = urlReader.getResponse();
		System.out.println(getInfo(weatherAppInfo));
	}

	private static String getInfo(String weatherAppInfo) {
		String output = "";
		JsonObject jsonInfo = new JsonParser().parse(weatherAppInfo).getAsJsonObject();
		JsonObject jsonInfoSys = new JsonParser().parse(jsonInfo.get("sys").toString()).getAsJsonObject();
		JsonObject jsonInfoMain = new JsonParser().parse(jsonInfo.get("main").toString()).getAsJsonObject();
		output += "Place: " + jsonInfoSys.get("country") + ", " + jsonInfo.get("name") + "\n";
		output += "Temperature: " + jsonInfoMain.get("temp") + ", Pressure: " + jsonInfoMain.get("pressure") + ", Humidity: " + jsonInfoMain.get("humidity") + "\n";
		
		return output;
	}
}

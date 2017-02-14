package week16;

import java.util.Scanner;

public class WebCrawler {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		URLReader urlReader = new URLReader();
		
		urlReader.setPageURL(sc.nextLine());
		String website = urlReader.getResponse();
		
		System.out.println(website);
	}
}

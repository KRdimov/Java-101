package week05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MyOwnMekiciSolver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int inputLines = sc.nextInt();
		int mekiciCount = sc.nextInt();
		int kotloniCount = sc.nextInt();
		Map<Integer, Integer> cookTimeMekici = new TreeMap<>();
		
		for (int i = 0; i < kotloniCount; i++) {
			cookTimeMekici.put(sc.nextInt(), 0);
		}
		
//		while (mekiciCount > 0) {
//			for(Map.Entry<Integer, Integer> cookTimeMekica : cookTimeMekici.entrySet()) {
//				if(mekiciCount > 0) {
//					cookTimeMekica.setValue(cookTimeMekica.getValue() + 1);
//					mekiciCount--;
//					continue;
//				}
//				break;
//			}
//		}
	}
}

package week05;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.Collections;

public class MekiciSolver {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			int t = sc.nextInt();
			for (int i = 0; i < t; i++) {
				System.out.println(solve(sc));
			}
		}
	}

	private static int solve(Scanner sc) {
		int mekiciCount = sc.nextInt();
		int kotloniCount = sc.nextInt();
		ArrayList<Integer> kotloni = new ArrayList<Integer>();
		for (int i = 0; i < kotloniCount; i++) {
			kotloni.contains(sc.nextInt());
		}
		
		int minimumTime = calculateMinimumTime(mekiciCount, kotloniCount, kotloni);;
		return minimumTime;
	}

	private static int calculateMinimumTime(int mekiciCount, int kotloniCount, ArrayList<Integer> kotloni) {
		int low = 0;
		int high = Collections.max(kotloni) * mekiciCount;
		
		while(low < high) {
			int mid = low + ((high - low) / 2);
			if(canCookMekici(mekiciCount, kotloniCount, kotloni, mid)) 
				high = mid;
			else
				low = mid + 1;
		}
		return low;
	}

	private static boolean canCookMekici(int mekiciCount, 
			int kotloniCount, ArrayList<Integer> kotloni, int availableTime) {
		int cookedMekici = 0;
		for (Integer kotlon : kotloni) {
			cookedMekici += availableTime / kotlon;
		}
		return cookedMekici>= mekiciCount;
	}
	
	
	
}

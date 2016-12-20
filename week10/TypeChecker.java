package week10;

import java.util.HashMap;
import java.util.Scanner;

public class TypeChecker {
	private static HashMap<String, String[]> functionCharacteristics = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while(!input.contains(".")) {
			if(input.equals("")) {
				input = sc.nextLine();
				continue;
			}
			String[] functionElements = input.split("::");
			String functionName = functionElements[0].trim();
			String[] functionTypes = functionElements[1].split("->");
			functionTypes[0] = functionTypes[0].trim();
			functionTypes[1] = functionTypes[1].trim();
			functionCharacteristics.put(functionName, functionTypes);
			input = sc.nextLine();
		}
		
		String[] composition = input.split(" . ");
		composition = reverseComposition(composition);
		System.out.println(isCompositionValid(composition));
	}
	
	private static boolean isCompositionValid(String[] composition) {
		String nextInput = functionCharacteristics.get(composition[0])[0];
		for (int i = 0; i < composition.length; i++) {
			if(functionCharacteristics.get(composition[i])[0].equals(nextInput)) {
				nextInput = functionCharacteristics.get(composition[i])[1];
				continue;
			}
			return false;
		}
		return true;
	}
	private static String[] reverseComposition(String[] composition) {
		for (int i = 0; i < composition.length / 2; i++) {
			String temp = composition[i];
			composition[i] = composition[composition.length - i - 1];
			composition[composition.length - i - 1] = temp;
		}
		return composition;
	}
}

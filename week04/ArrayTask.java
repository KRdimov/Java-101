package week04;

import java.util.Scanner;

public class ArrayTask {
	private static Scanner sc = new Scanner(System.in);

//	public static void main(String[] args) {
//		int[] array = new int[] {
//			1, 5, 8, 10, 0, 5, 0
//		};
//		int n = sc.nextInt();
//		int result = calculate(array, n);
//		System.out.println(result);
//	}

	private static int calculate(int[] array, int n) {
		if(n >= 0 && n < array.length && array.length > 0)
			array[n] = 1;
		else 
			return -1;
		int result = 1;
		for (int i = 0; i < array.length; i++) {
			result *= array[i];
		}
		return result;
	}
}

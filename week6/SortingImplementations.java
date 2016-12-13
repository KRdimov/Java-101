package week6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public final class SortingImplementations {
	private static int[] elements = new int[] {
		15, 21, 13, 14, 10, 10, 19, 18, 21, 18, 19, 6, 14, 2, 9, 
		1, 20, 6, 27, 23, 23, 27, 12, 11, 9, 14, 13, 4, 19, 28
	};
	
	public static void main(String[] args) {
		sherlockAndArray();
	}
	
	private static void printArray() {
		for (int i = 0; i < elements.length; i++) {
			System.out.println(elements[i]);
		}
	}

	public static void selectionSort() {
		int index = 0;
		while(index < elements.length) {
			int minimalElement = elements[index];
			int minElementOldIndex = index;
			for (int i = index + 1; i < elements.length; i++) {
				if(minimalElement > elements[i]) {
					minimalElement = elements[i];
					minElementOldIndex = i;
				}
			}
			int temp = elements[index];
			elements[index++] = minimalElement;
			elements[minElementOldIndex] = temp;
		}
	}
	
	public static void insertionSort() {
		int indexReached = 1;
		while(indexReached < elements.length) {
			int startIndex = indexReached;
			while(indexReached > 0 && elements[indexReached] < elements[indexReached - 1]) {
				int temp = elements[indexReached];
				elements[indexReached] = elements[indexReached - 1];
				elements[indexReached - 1] = temp;
				indexReached--;
			}
			indexReached = startIndex + 1;
		}
	}

	public static void bubbleSort() {
		boolean swapMade = true;
		while(swapMade) {
			swapMade = false;
			for (int i = 0; i < elements.length - 1; i++) {
				if(elements[i] > elements[i + 1]) {
					swapMade = true;
					int temp = elements[i];
					elements[i] = elements[i + 1]; 
					elements[i + 1] = temp;
				}
			}
		}
	}
	
	public static void countingSort() {
		int k = getBiggestElement(elements);
		int[] numIndexedArr = new int[k + 1];
		for (int i = 0; i < elements.length; i++) 
			numIndexedArr[elements[i]]++;
		
		for (int i = 1; i < k + 1; i++)
			numIndexedArr[i] = numIndexedArr[i] + numIndexedArr[i - 1];
		
		int[] sorted = new int[elements.length];
		for (int i = 0; i < elements.length; i++) {
			numIndexedArr[elements[i]]--;
			sorted[numIndexedArr[elements[i]]] = elements[i];
		}
		elements = sorted;
	}

	private static int getBiggestElement(int[] elements) {
		int biggest = Integer.MIN_VALUE;
		for (int i = 0; i < elements.length; i++) {
			if(biggest < elements[i])
				biggest = elements[i];
		}
		return biggest;
	}

	public static void missingNumbers() {
		int[] A, B;
		Scanner sc = new Scanner(System.in);
		
		int nA = Integer.parseInt(sc.nextLine());
		A = new int[nA];
		String[] temp = sc.nextLine().split(" ");
		fillArray(A, temp);
		int nB = Integer.parseInt(sc.nextLine());
		B = new int[nB];
		temp = sc.nextLine().split(" ");
		fillArray(B, temp);
		
		printMissingNumbers(A, B);
	}

	private static void printMissingNumbers(int[] A, int[] B) {
		int biggestEl = getBiggestElement(B);
		int[] temp = new int[biggestEl + 1];
		for (int i = 0; i < B.length; i++) {
			temp[B[i]]++;
		}
		for (int i = 0; i < A.length; i++) {
			temp[A[i]]--;
		}
		for (int i = 0; i < temp.length; i++) {
			if(temp[i] != 0)
				System.out.print(i + " ");
		}
	}

	private static void fillArray(int[] array, String[] temp) {
		for (int i = 0; i < temp.length; i++) {
			array[i] = Integer.parseInt(temp[i]);
		}
	}

	public static void sherlockAndArray() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> output = new ArrayList<String>();
		int testCases = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < testCases; i++) {
			int n = Integer.parseInt(sc.nextLine());
			int[] array = new int[n];
			fillArray(array, sc.nextLine().split(" "));
			output.add((followsRule(array) ? "YES" : "NO"));
		}
		for (String line : output) {
			System.out.println(line);
		}
	}

	private static boolean followsRule(int[] array) {
		boolean followsRule = false;
		int totalSum = sumArray(array);
		int left = 0;
		int right = totalSum;
		for(int i = 0; i < array.length; i++){
			if(i != 0)
				left += array[i - 1];
			
			right -= array[i];
			
			if (left == right) {
		    	followsRule = true;
		    	break;
		    }
		}
		return followsRule;
	}

	private static int sumArray(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}
}

package week4;

import week3_code_improvement.MySinglyLinkedList;

public class Main {

	public static void main(String[] args) {
//		Integer[] arr = new Integer[] {
//			4, 2, 1, 9, 12, 10	
//		};
		BinarySearchTree tree1 = new BinarySearchTree();
		BinarySearchTree tree2 = new BinarySearchTree();
		tree1.insert(20);
		tree2.insert(20);
		tree1.insert(10);
		tree2.insert(10);
		tree1.insert(30);
		tree2.insert(30);
		tree1.insert(25);
		tree2.insert(25);
		
		System.out.println(tree1.equal(tree2));
	}
	
	private static int interpolationSearch(int number, int[] arr) {
		int l = 0;
		int r = arr.length - 1;
		while(l <= r) {
			if(arr[l] == arr[r]) {
				if(arr[l] == number)
					return l;
				else
					return -1;
			}
			float k = (float)(number - arr[l])/(arr[r] - arr[l]);
			if(k < 0 || k > 1)
				return -1;
			float expr = ((float)l) + k * ((float)(r - l));
			int mid = Math.round(expr);
			
			if (number < arr[mid]) {
				r =mid - 1;
			} else if (number > arr[mid]) {
				l = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	private static int binarySearch(int number, int[] arr) {
		int length = arr.length - 1;
		int middle = length / 2;
		while(middle <= length) {
			if(arr[middle] > number) {
				length = middle;
				middle = length / 2;
			} else if(arr[middle] < number) 
				middle = (length + middle + 1) / 2;
			 else
				return middle;
		}
		return -1;
	}
	
	private static int upperBound(int number, int[] arr) {
		int length = arr.length - 1;
		int middle = length / 2;
		while(middle <= length) {
			if(arr[middle] > number) {
				length = middle;
				middle = length / 2;
			} else if(arr[middle] < number) 
				middle = (length + middle + 1) / 2;
			 else {
				 if(middle == arr.length - 1)
					 return -1;
				 else
					 return middle + 1;
			 }
				
		}
		return -1;
	}

	public static MyStackInterface<Integer> sortWithStacks(MyStackInterface<Integer> unsorted) {
		MyStackInterface<Integer> tempSt = new MySinglyLinkedList<>();
		while(unsorted.getSize() > 0) {
			Integer element = unsorted.pop();
			if(tempSt.getSize() == 0)
				tempSt.push(element);
			else {
				if(element.intValue() >= tempSt.peek().intValue())
					tempSt.push(element);
				else
					pushInCorrectSpot(element, unsorted, tempSt);
			}
		}
		return tempSt;
	}

	private static void pushInCorrectSpot(Integer element, MyStackInterface<Integer> unsorted,
			MyStackInterface<Integer> tempSt) {
		int countReturnedElements = 0;
		while(tempSt.getSize() > 0) {
			if(tempSt.peek().intValue() > element.intValue()) {
				countReturnedElements++;
				unsorted.push(tempSt.pop());
			} else {
				tempSt.push(element);
				break;
			}
		}
		if(tempSt.getSize() == 0)
			tempSt.push(element);
		while (countReturnedElements-- > 0) 
			tempSt.push(unsorted.pop());
	}
}

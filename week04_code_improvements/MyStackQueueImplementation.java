package week04_code_improvements;

import week03_code_improvements.MySinglyLinkedList;
import week04.MyQueueInterface;

public class MyStackQueueImplementation<T extends Comparable<T>> {
	MyQueueInterface<T> q1;
	MyQueueInterface<T> q2;

	public MyStackQueueImplementation() {
		q1 = new MySinglyLinkedList<>();
		q2 = new MySinglyLinkedList<>();
	}
	
	
	
}

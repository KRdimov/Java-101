package week04;

import week03_code_improvements.MySinglyLinkedList;

public class MyQueue<T extends Comparable<T>> {
	private MyStackInterface<T> s1;
	private MyStackInterface<T> s2;
	private int size;
	
	public MyQueue() {
		s1 = new MySinglyLinkedList<>();
		s2 = new MySinglyLinkedList<>();
	}
	
	public void enqueue(T element) {
		s1.push(element);
		size++;
	}
	
	public T peek() {
		if(s2.getSize() == 0)
			fillSecondStack();
		return s2.peek();
	}

	private void fillSecondStack() {
		while(s1.getSize() > 0)
			s2.push(s1.pop());
	}
	
	public T dequeue() {
		if(s2.getSize() == 0)
			fillSecondStack();
		size--;
		return s2.pop();
	}
	
	public int getSize() {
		return size;
	}
}

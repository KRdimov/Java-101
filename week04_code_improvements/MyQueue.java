package week04_code_improvements;

import week03_code_improvements.MySinglyLinkedList;
import week04.MyQueueInterface;

public class MyQueue<T extends Comparable<T>> implements MyQueueInterface<T> {
	private MyQueueInterface<T> elements;
	
	public MyQueue() {
		elements = new MySinglyLinkedList<>();
	}
	
	@Override
	public void enqueue(T element) {
		elements.enqueue(element);
	}

	@Override
	public T dequeue() {
		return elements.dequeue();
	}

	@Override
	public T peek() {
		return elements.peek();
	}

	@Override
	public int getSize() {
		return elements.getSize();
	}

}

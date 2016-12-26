package week04_code_improvements;

import week03_code_improvements.MySinglyLinkedList;
import week04.MyStackInterface;

public class MyStack<T extends Comparable<T>> implements MyStackInterface<T>{
	private MyStackInterface<T> elements;
	private MyStackInterface<T> minElementHolder;
	
	public MyStack() {
		elements = new MySinglyLinkedList<>();
		minElementHolder = new MySinglyLinkedList<>();
	}
	
	@Override
	public void push(T element) {
		elements.push(element);
		insertMinElement(element);
	}

	private void insertMinElement(T element) {
		if(minElementHolder.getSize() == 0) {
			minElementHolder.push(element);
		} else {
			if(element.compareTo(minElementHolder.peek()) < 0) {
				minElementHolder.push(element);
			} else {
				minElementHolder.push(minElementHolder.peek());
			}
		}
	}
	
	public T min() {
		return minElementHolder.peek();
	}

	@Override
	public T peek() {
		return elements.peek();
	}

	@Override
	public T pop() {
		minElementHolder.pop();
		return elements.pop();
	}

	@Override
	public int getSize() {
		return elements.getSize();
	}

}

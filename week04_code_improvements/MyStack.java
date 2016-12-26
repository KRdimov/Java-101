package week04_code_improvements;

import week03_code_improvements.MySinglyLinkedList;
import week04.MyStackInterface;

public class MyStack<T extends Comparable<T>> implements MyStackInterface<T>{
	private MyStackInterface<T> elements;
	
	public MyStack() {
		elements = new MySinglyLinkedList<>();
	}
	
	@Override
	public void push(T element) {
		elements.push(element);
	}

	@Override
	public T peek() {
		return elements.peek();
	}

	@Override
	public T pop() {
		return elements.pop();
	}

	@Override
	public int getSize() {
		return elements.getSize();
	}

}

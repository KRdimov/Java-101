package week04_code_improvements;

import week03_code_improvements.MySinglyLinkedList;
import week04.MyQueueInterface;
import week04.MyStackInterface;

public class MyQueueStackImplementation<T extends Comparable<T>> implements MyQueueInterface<T> {
	private MyStackInterface<T> s1;
	private MyStackInterface<T> s2;
	
	public MyQueueStackImplementation() {
		s1 = new MySinglyLinkedList<>();
		s2 = new MySinglyLinkedList<>();
	}
	
	@Override
	public void enqueue(T element) {
		transferElements(false);
		s1.push(element);
	}

	@Override
	public T dequeue() {
		transferElements(true);
		return s2.pop();
	}

	private void transferElements(boolean s1ToS2) {
		if(s1ToS2) {
			while(s1.getSize() > 0) {
				s2.push(s1.pop());
			}
		} else {
			while(s2.getSize() > 0) {
				s1.push(s2.pop());
			}
		}
	}

	@Override
	public T peek() {
		transferElements(true);
		return s2.peek();
	}

	@Override
	public int getSize() {
		return s2.getSize() + s1.getSize();
	}

}

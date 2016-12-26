package week04_code_improvements;

import week03_code_improvements.MySinglyLinkedList;
import week04.MyQueueInterface;
import week04.MyStackInterface;

public class MyStackQueueImplementation<T extends Comparable<T>> implements MyStackInterface<T>{
	private MyQueueInterface<T> q1;
	private MyQueueInterface<T> q2;
	private boolean fromQ1ToQ2;

	public MyStackQueueImplementation() {
		q1 = new MySinglyLinkedList<>();
		q2 = new MySinglyLinkedList<>();
		fromQ1ToQ2 = true;
	}

	@Override
	public void push(T element) {
		if(fromQ1ToQ2) {
			while(q1.getSize() > 0) {
				q2.enqueue(q1.dequeue());
			}
			q1.enqueue(element);
			fromQ1ToQ2 = false;
		} else {
			while(q2.getSize() > 0) {
				q1.enqueue(q2.dequeue());
			}
			q2.enqueue(element);
			fromQ1ToQ2 = true;
		}
	}

	@Override
	public T pop() {
		T element = null;
		if(fromQ1ToQ2) {
			if(q2.getSize() > 0) {
				element = q2.dequeue();
				if(q1.getSize() > 0) {
					q2.enqueue(q1.dequeue());
				}
			}
		} else {
			if(q1.getSize() > 0) {
				element = q1.dequeue();
				if(q2.getSize() > 0) {
					q1.enqueue(q2.dequeue());
				}
			}
		}
		return element;
	}

	@Override
	public T peek() {
		T element = null;
		if(fromQ1ToQ2) {
			if(q2.getSize() > 0) {
				element = q2.peek();
			}
		} else {
			if(q1.getSize() > 0) {
				element = q1.peek();
			}
		}
		return element;
	}

	@Override
	public int getSize() {
		return q1.getSize() + q2.getSize();
	}
}

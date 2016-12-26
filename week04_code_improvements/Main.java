package week04_code_improvements;

import week03_code_improvements.MySinglyLinkedList;
import week04.MyStackInterface;

public class Main {
	private static int[] array = {
		4, 2, 12, 0, 1, 9, 6
	};
	
	public static void main(String[] args) {
		sortArrayWithStacks();
	}
	
	private static void sortArrayWithStacks() {
		MyStackInterface<Integer> s1 = new MySinglyLinkedList<>();
		MyStackInterface<Integer> s2 = new MySinglyLinkedList<>();
		
		for (int i = 0; i < array.length; i++) {
			if(s1.getSize() == 0) {
				s1.push(array[i]);
			} else {
				if(s1.peek() > array[i]) {
					while(s1.peek() != null && s1.peek() > array[i]) {
						s2.push(s1.pop());
					}
					s1.push(array[i]);
					while(s2.getSize() > 0) {
						s1.push(s2.pop());
					}
				} else {
					s1.push(array[i]);
				}
			}
		}
		
		while(s1.getSize() > 0) {
			System.out.println(s1.pop());
		}
	}
	
	private static void testMinElementInStack() {
		MyStack<Integer> stack = new MyStack<>();
		stack.push(2);
		stack.push(1);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(0);
		stack.push(12);
		System.out.println(stack.min());
		System.out.println(stack.pop());
		System.out.println(stack.min());
		System.out.println(stack.pop());
		System.out.println(stack.min());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.min());
		System.out.println(stack.pop());
	}

	private static void testStackQueueImplementation() {
		MyStackQueueImplementation<Integer> stack = new MyStackQueueImplementation<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		stack.push(5);
		System.out.println(stack.getSize());
		stack.push(6);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
	}
	
	private static void testQueueStackImpementation() {
		MyQueueStackImplementation<Integer> queue = new MyQueueStackImplementation<>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.enqueue(5);
		System.out.println(queue.getSize());
		queue.enqueue(6);
		System.out.println(queue.dequeue());
		System.out.println(queue.getSize());
		System.out.println(queue.dequeue());
		System.out.println(queue.peek());
		System.out.println(queue.dequeue());
	}
}

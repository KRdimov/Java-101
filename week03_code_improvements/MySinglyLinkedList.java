package week03_code_improvements;

import week03.MyLinkedListInterface;
import week04.MyQueueInterface;
import week04.MyStackInterface;

public class MySinglyLinkedList<T extends Comparable<T>> implements MyLinkedListInterface<T>,
MyStackInterface<T>, MyQueueInterface<T>{
	private Node head;
	// the rightmost element of the Linked List
	private Node tail;
	private int size;
	
	public MySinglyLinkedList() {
		head = new Node();
		tail = new Node();
		size = 0;
	}

	@Override
	public void addFirst(T element) {
		if(size == 0){
			head.element = element;
			tail = head;
		} else {
			Node node = new Node(element, head);
			head = node;
		}
		size++;
	}

	@Override
	public void addLast(T element) {
		if(size == 0)
			this.addFirst(element);
		else {
			Node node = new Node(element);
			tail.next = node;
			tail = node;
			size++;	
		}
	}

	@Override
	public void add(T newElement, int index) {
		if(index < 0 || index > size) {
			System.out.println("Index out of bounds");
			return;
		}
		if(index == 0) 
			this.addFirst(newElement);
		else if(index == size) 
			this.addLast(newElement);
		else {
			Node node = new Node(newElement);
			Node p1 = getIndexNode(index - 1);
			node.next = p1.next;
			p1.next = node;
			size++;
		}
	}

	@Override
	public T getFirst() {
		return head.element;
	}

	@Override
	public T getLast() {
		return tail.element;
	}

	@Override
	public T get(int index) {
		if(index < 0 || index >= size) {
			System.out.println("Index out of bounds");
			return null;
		}
		Node p1 = getIndexNode(index);
		return p1.element;
	}

	private MySinglyLinkedList<T>.Node getIndexNode(int index) {
		Node p1 = head;
		int i = 0;
		while(i != index) {
			p1 = p1.next;
			i++;
		}
		return p1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void remove(int index) {
		if(index < 0 || index >= size) {
			System.out.println("Index out of bounds");
			return;
		}
		if(index == 0 && size == 1) {
			head = new Node();
			tail = new Node();
		} else if(index == 0)
			head = head.next;
		else {
			Node p1 = getIndexNode(index - 1);
			if(p1.next.next == null) {
				p1.next = null;
				tail = p1;
			}
			else
				p1.next = p1.next.next;
		}
		size--;
	}

	@Override
	public void addList(MyLinkedListInterface<T> list) {
		for (int i = 0; i < list.size(); i++) {
			this.addLast(list.get(i));
		}
	}
	
	@Override
	public String toString() {
		String output = "[";
		Node p1 = head;
		while(p1 != null) {
			output += " " + p1.element;
			p1 = p1.next;
		}
		return output + "]";
	}
	
	public T getKthFromLast(int k) {
		Node p1 = head;
		Node p2 = head;
		if(k == 0)
			return tail.element;
		int i = 0;
		while(i < k) {
			p2 = p2.next;
			if(p2 == null) {
				System.out.println("Element out of bounds");
				return null;
			}
			i++;
		}
		while(p2 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1.element;
	}
	
	private class Node {
		T element;
		Node next;
		
		public Node() {
			
		}
		
		public Node(T element) {
			this.element = element;
			this.next = null;
		}
		
		public Node(T element, Node node) {
			this.element = element;
			this.next = node;
		}
	}

	@Override
	public void enqueue(T element) {
		this.addLast(element);
	}

	@Override
	public T dequeue() {
		return removeHead();
	}

	@Override
	public void push(T element) {
		this.addFirst(element);
	}

	@Override
	public T peek() {
		T element;
		if(size == 0) {
			element = null;
		} else {
			element = head.element;
		}
		return element;
	}

	@Override
	public T pop() {
		return removeHead();
	}

	private T removeHead() {
		T element;
		if(size == 0) {
			element = null;
		} else {
			element = head.element;
			this.remove(0);
		}
		return element;
	}

	@Override
	public int getSize() {
		return this.size;
	}
}

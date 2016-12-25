package week03;

public class Node<T> {
	T element;
	Node<T> next;
	
	public Node() {
		
	}
	
	public Node(T element) {
		this.element = element;
		this.next = null;
	}
	
	public Node(T element, Node<T> node) {
		this.element = element;
		this.next = node;
	}
}

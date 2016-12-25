package week03;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
	public Node<T> head;
	public Node<T> last;
	
	public MyLinkedList() {
		head = new Node<T>();
		last = new Node<T>();
	}
	
	// delete only with single node info
	public void delete(Node<Integer> node) {
		node.element = node.next.element;
		node.next = node.next.next;
	}
	
	@Override
	public void addFirst(T element) {
		Node<T> node = new Node<>(element, head);
		this.head = node;
		if(last.element == null) {
			last = head;
			head.next = new Node<>();
		}
	}

	@Override
	public void addLast(T element) {
		if(head.element == null) {
			this.addFirst(element);
			return;
		}
		Node<T> node = new Node<>(element, null);
		this.last.next = node;
		this.last = node;
		this.last.next = new Node<>();
	}

	@Override
	public void add(T newElement, int index) {
		Node<T> node = head;
		Node<T> newNode = new Node<>(newElement, null);
		
		if(index == 0) {
			this.addFirst(newElement);
			return;
		}
		if(index < 0) {
			System.out.println("Index out of range");
			return;
		}
		
		for (int i = 0; i < index - 1; i++) {
			if(node.next != null) 
				node = node.next;
			else{
				System.out.println("Index out of range");
				return;
			}
		}
		
		if(node.next == null) {
			node.next = newNode;
		} else {
			newNode.next = node.next;
			node.next = newNode;
		}
	}

	@Override
	public T getFirst() {
		return head.element;
	}

	@Override
	public T getLast() {
		return last.element;
	}

	@Override
	public T get(int index) {
		Node<T> node = head;
		if(index < 0) {
			System.out.println("Index out of range");
			return null;
		}
		for (int i = 0; i < index; i++) {
			if(node.next != null) 
				node = node.next;
			else {
				System.out.println("Index out of range");
				return null;
			}
		}
		return node.element;
	}

	@Override
	public int size() {
		Node<T> node = head;
		int counter = 0;
		while (node.next != null) {
			node = node.next;
			counter ++;
		}
		return counter;
	}

	@Override
	public void remove(int index) {
		Node<T> node = head;
		for (int i = 0; i < index - 1; i++) {
			if(node.next != null) 
				node = node.next;
			else {
				System.out.println("Index out of range");
				return;
			}
		}
		if(node.next == null) {
			System.out.println("Index out of range");
			return;
		} else {
			node.next = node.next.next;
		}
	}

	@Override
	public void addList(MyLinkedListInterface<T> list) {
		for (int i = 0; i < list.size(); i++) {
			this.addLast(list.get(i));
		}
	}
	
	public String toString() {
		String output = "[";
		Node<T> node = head;
		if(node == null) 
			return "[]";
		
		while (true) {
			output += node.element + "->";
			node = node.next;
			if(node.next == null) {
				output += node.element;
				break;
			}
		}
		output += "]";
		return output;
	}
}
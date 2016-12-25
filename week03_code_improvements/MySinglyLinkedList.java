package week03_code_improvements;

import java.util.HashSet;

import week03.MyLinkedListInterface;
import week04.MyQueueInterface;
import week04.MyStackInterface;

public class MySinglyLinkedList<T extends Comparable<T>> implements MyLinkedListInterface<T>,
MyStackInterface<T>, MyQueueInterface<T>{
	private Node head;
	// the rightmost element of the Linked List
	private Node tail;
	private int size;
	
	/**
	 * Two lists for testing purposes only
	 */
	private MySinglyLinkedList<T> listOne;
	private MySinglyLinkedList<T> listTwo;
	
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
		if(index != size - 1) {
			Node toBeDeleted = getIndexNode(index);
			delete(toBeDeleted);
		} else {
			Node newLastNode = getIndexNode(index - 1);
			newLastNode.next = null;
			tail = newLastNode;
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
		boolean first = true;
		while(p1 != null) {
			if(first) {
				output += p1.element;
				first = false;
			} else {
				output += " " + p1.element;
			}
			p1 = p1.next;
		}
		return output + "]";
	}
	
	/**
	 * @param k - index from last element
	 * O(n) complexity
	 * @return the element at k index from last element or null
	 */
	public T kthToLast(int k) {
		T element = null;
		if(k < 0 || k > this.size) {
			return element;
		}
		Node p1 = this.head;
		Node p2 = this.head;
		
		for (int i = 0; i < k; i++) {
			p2 = p2.next;
		}
		while(p2 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		element = p1.element;
		return element;
	}
	
	/**
	 * delete function that deletes first or middle elements in list
	 * @param node - Node element that will be deleted
	 * O(1) complexity
	 */
	private void delete(Node node) {
		Node temp = node.next;
		node.next = temp.next;
		node.element = temp.element;
		temp = null;
	}
	
	/**
	 * Partitions the list in such a way that
	 * elements smaller than x are placed on the left from x and
	 * elements bigger or equal to x are placed on the right from x
	 * @param x - Element to partition list around
	 * O(n*n) complexity
	 */
	public void partitionList(T x) {
		if(x == null) {
			System.out.println("Can't partition list around parameter x = null");
			return;
		}
		MySinglyLinkedList<T> partitionedList = new MySinglyLinkedList<>();
		partitionedList.addFirst(x);
		while(!(head == null)) {
			if(head.element.compareTo(x) >=0) {
				partitionedList.addLast(head.element);
			} else {
				partitionedList.addFirst(head.element);
			}
			head = head.next;
		}
		head = partitionedList.head;
		tail = partitionedList.tail;
	}
	
	/**
	 * Wrapper function for checkIfTwoListsAreConnected()
	 * @return element - the common node's element or null
	 */
	public T getFirstCommonElement() {
		Node node = checkIfTwoListsAreConnected();
		if(node == null) 
			return null;
		else
			return node.element;
	}
	
	/**
	 * Finds the first common node in the two lists if there is one
	 * otherwise returns null
	 * O(n) complexity
	 * @return commonNode - the first node that is common in two lists or null
	 */
	private Node checkIfTwoListsAreConnected() {
		initializeTwoListsWithCommonNodes();
		HashSet<Node> nodeSet = new HashSet<>();
		Node commonNode = null;
		
		Node p1 = listOne.head;
		while(p1 != null) {
			nodeSet.add(p1);
			p1 = p1.next;
		}
		Node p2 = listTwo.head;
		while(p2 != null) {
			if(nodeSet.contains(p2)){
				commonNode = p2;
				break;
			}
			p2 = p2.next;
		}
		return commonNode;
	}
	
	/**
	 * Manually initialize the two lists so that they have common nodes
	 * listOne =  A->B->C->D
	 * listTwo = F->C->D
	 */
	private void initializeTwoListsWithCommonNodes() {
		Node c = new Node((T) new Character('C'));
		Node d = new Node((T) new Character('D'));
		
		listOne = new MySinglyLinkedList<>();
		listTwo = new MySinglyLinkedList<>();
		
		listOne.head = new Node((T) new Character('A'));
		listOne.head.next = new Node((T) new Character('B'));
		listOne.head.next.next = c;
		listOne.head.next.next.next = d;
		listOne.tail = listOne.head.next.next.next;
		
		listTwo.head = new Node((T) new Character('F'));
		listTwo.head.next = c;
		listTwo.head.next.next = d;
		listTwo.tail = listTwo.head.next.next;
	}

	/**
	 * Manually initialize the two lists so that they don't have common nodes
	 * listOne = A->B->C->D
	 * listTwo = F->G->H
	 */
	private void initializeTwoListsWithoutCommonNodes() {
		listOne = new MySinglyLinkedList<>();
		listTwo = new MySinglyLinkedList<>();
		
		listOne.head = new Node((T) new Character('A'));
		listOne.head.next = new Node((T) new Character('B'));
		listOne.head.next.next = new Node((T) new Character('C'));;
		listOne.head.next.next.next = new Node((T) new Character('D'));;
		listOne.tail = listOne.head.next.next.next;
		
		listTwo.head = new Node((T) new Character('F'));
		listTwo.head.next = new Node((T) new Character('G'));;
		listTwo.head.next.next = new Node((T) new Character('H'));;
		listTwo.tail = listTwo.head.next.next;
	}
	
	/**
	 * Detects if the list has a loop
	 * @return true or false
	 */
	public boolean checkIfListHasLoop() {
		initializeListWithLoop();
		boolean firstIteration = true;
		Node h1 = listOne.head;
		Node h2 = listOne.head;
		if(h1 == null) {
			return false;
		} else  {
			while(firstIteration || h1 != h2) {
				h1 = h1.next;
				if(h1 == null) {
					return false;
				}
				h2 = h2.next;
				if(h2 == null || h2.next == null) {
					return false;
				}
				h2 = h2.next;
				firstIteration = false;
			}
		}
		return true;
	}
	
	/**
	 * Manually initialize the list so that it has a loop
	 * listOne = A->B->C->D->B
	 */
	private void initializeListWithLoop() {
		listOne = new MySinglyLinkedList<>();
		Node b = new Node((T) new Character('B'));
		
		listOne.head = new Node((T) new Character('A'));
		listOne.head.next = b;
		listOne.head.next.next = new Node((T) new Character('C'));
		listOne.head.next.next.next = new Node((T) new Character('D'));
		listOne.head.next.next.next.next = b;
		listOne.tail = b.next.next;
	}

	/**
	 * Manually initialize the list so that it has no loop
	 * listOne = A->B->C->D->F
	 */
	private void initializeListWithoutLoop() {
		listOne = new MySinglyLinkedList<>();
		
		listOne.head = new Node((T) new Character('A'));
		listOne.head.next = new Node((T) new Character('B'));
		listOne.head.next.next = new Node((T) new Character('C'));
		listOne.head.next.next.next = new Node((T) new Character('D'));
		listOne.head.next.next.next.next = new Node((T) new Character('F'));
		listOne.tail = listOne.head.next.next.next.next;
	}
	
	public T getFirstLoopElement() {
		if(checkIfListHasLoop()) {
			//TODO
		} 
		return null;
	}
	
	/**
	 * Checks if the list is palindrome
	 * O(n) complexity
	 * @return true or false
	 */
	public boolean isListPalindrome() {
		if(head == null)
	        return false;
	 
	    Node p = head;
	    Node pReverse = new Node(head.element);
	 
	    while(p.next != null){
	    	Node temp = new Node(p.next.element);
	        temp.next = pReverse;
	        pReverse = temp;
	        p = p.next;
	    }
	 
	    Node p1 = head;
	    Node p2 = pReverse;
	 
	    while(p1 != null){
	        if(p1.element != p2.element)
	            return false;
	 
	        p1 = p1.next;
	        p2 = p2.next;
	    }
	    return true;
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

package week3;

public class Main {

	public static void main(String[] args) {
		MyLinkedList<Integer> myList = new MyLinkedList<>();
		MyLinkedList<Integer> myList2 = new MyLinkedList<>();
		Node<Integer> p1 = new Node<>(12, null);
		for (int i = 0; i <= 10; i++) {
			myList.addLast(i);
			myList2.addFirst(i * 20);
		}
		myList.last.next = p1;
		myList.last = p1;
		p1 = new Node<>(13, null);
		myList.last.next = p1;
		myList.last = p1;
		p1 = new Node<>(12, null);
		myList2.last.next = p1;
		myList2.last = p1;
		p1 = new Node<>(13, null);
		myList2.last.next = p1;
		myList2.last = p1;
		System.out.println(myList);
		System.out.println(myList2);
		Node<Integer> common = commonNode(myList, myList2);
		System.out.println(common.element);
		myList2.addList(myList);
 		myList2.remove(10);
		Integer i = getKthFromLast(myList2, 10);
		System.out.println(myList2);
		System.out.println(i);
		testDelete(myList2);
		System.out.println(myList2);
		myList2 = partition(25, myList2);
		System.out.println(myList2);
		
	}
	
	public static Node<Integer> commonNode(MyLinkedList<Integer> list1, MyLinkedList<Integer> list2) {
		Node<Integer> p1 = list1.head;
		int list2Size = list2.size();
		Node<Integer> temp = new Node<>();
		while (p1 != null) {
			temp = p1.next;
			p1.next = null;
			p1 = temp;
		}
		
		list1.head = null;
		list1.last = null;
		
		if(list2Size == list2.size()) 
			return null;
		else {
			p1 = list2.head;
			while (p1.next != null) {
				p1 = p1.next;
			}
			return p1;
		}
	}
	

	public static MyLinkedList<Integer> partition(Integer x, MyLinkedList<Integer> myList) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.addFirst(x);
		int index = 0;
		Node<Integer> p1 = myList.head;
		for (int i = 0; i < myList.size(); i++) {
			if(p1.element < x) {
				list.addFirst(p1.element);
				index++;
			}
			else
				list.addLast(p1.element);
			
			p1 = p1.next;
		}
		list.remove(index);
		return list;
	}
	
	// delete with only one node info
	private static void testDelete(MyLinkedList<Integer> list) {
		Node<Integer> p1 = list.head;
		for (int i = 0; i < 7; i++) {
			p1 = p1.next;
		}
		list.delete(p1);
	}

	// kth from last
	public static Integer getKthFromLast(MyLinkedList<Integer> list, int k) {
		if(k < 1)
			return null;
		
		Node<Integer> p1 = list.head;
		Node<Integer> p2 = list.head;
		
		for (int i = 0; i < k + 1; i++) {
			if(p2 == null) {
				return null;
			}
			p2 = p2.next;
		}
		while(p2 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1.element;
	}

}

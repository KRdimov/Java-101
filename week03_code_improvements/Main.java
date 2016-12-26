package week03_code_improvements;

public class Main {

	public static void main(String[] args) {
		MySinglyLinkedList<Integer> ints = new MySinglyLinkedList<>();
		ints.addLast(0);
		ints.addLast(1);
		ints.addLast(2);
		ints.add(3, 0);
		ints.add(4, 2);
		ints.add(5, 5);
		ints.add(10, 3);
		ints.addFirst(-1);
		
		System.out.println(ints);
		System.out.println(ints.kthToLast(8));
		ints.remove(7);
		System.out.println(ints);
		ints.partitionList(2);
		System.out.println(ints);
		System.out.println("--------------");
		System.out.println(ints.getFirstCommonElement());
		System.out.println(ints.checkIfListHasLoop());
		System.out.println(ints.getFirstLoopElement());
	}
}

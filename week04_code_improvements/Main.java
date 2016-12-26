package week04_code_improvements;

public class Main {
	public static void main(String[] args) {
		testQueueStackImpementation();
	}
	
	public static void testQueueStackImpementation() {
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
		System.out.println(queue.dequeue());
	}
}

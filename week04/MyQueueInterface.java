package week04;

/**
 * 
 * @author milush
 *
 * @param <T> should implement the Comparable interface
 */

public interface MyQueueInterface<T extends Comparable<T>> {
	
	/**
	 * Adds an element to the queue on last place
	 * @param element to be added
	 */
	public void enqueue(T element);
	
	/**
	 * Removes an element from the beginning of the queue
	 * @return the element that was first added
	 */
	public T dequeue();
	
	/**
	 * Returns the element on first place of the Queue without removing it
	 * @return the last added element
	 */
	public T peek();
	
	/**
	 * Returns the number of elements in our Queue
	 * @return the number of elements in our Queue
	 */
	public int getSize();
}

package week04;


/**
 * Stack Interface
 * 
 * 
 * @param <T> should implement Comparable interface
 */

public interface MyStackInterface<T extends Comparable<T>> {
	
	/**
	 * Adds an element on top of the stack
	 * @param element to be added
	 */
	public void push(T element);
	
	
	/**
	 * Returns the element on the top of the Stack without removing it
	 * @return the last added element
	 */
	public T peek();
	
	/**
	 * Removes the element on the top of the Stack
	 * @return the last added element 
	 */
	public T pop();
	
	/**
	 * Returns the number of elements in our Stack
	 * @return the number of elements in our Stack
	 */
	public int getSize();
}

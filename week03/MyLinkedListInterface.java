package week03;

public interface MyLinkedListInterface<T>{
	
	public void addFirst(T element);
	
	public void addLast(T element);
	
	public void add(T newElement, int index);
	
	public T getFirst();
	
	public T getLast();
	
	public T get(int index);
	
	public int size();
	
	public void remove(int index);
	
	public void addList(MyLinkedListInterface<T> list);
}

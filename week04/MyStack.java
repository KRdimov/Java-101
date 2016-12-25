package week04;

import week03_code_improvements.MySinglyLinkedList;

public class MyStack implements MyStackInterface<Integer> {
	MyStackInterface<Integer> s;
	MyStackInterface<Integer> mins;
	
	public MyStack() {
		s = new MySinglyLinkedList<>();
		mins = new MySinglyLinkedList<>();
	}
	
	@Override
	public void push(Integer element) {
		if(s.getSize() == 0)
			mins.push(element);
		else if(mins.peek().intValue() > element.intValue())
			mins.push(element);
		else
			mins.push(mins.peek());
			
		s.push(element);
	}
	
	public Integer min() {
		return mins.peek();
	}

	@Override
	public Integer peek() {
		return s.peek();
	}

	@Override
	public Integer pop() {
		mins.pop();
		return s.pop();
	}

	@Override
	public int getSize() {
		return s.getSize();
	}

}

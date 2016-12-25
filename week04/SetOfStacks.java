package week04;

import java.util.ArrayList;

import week03_code_improvements.MySinglyLinkedList;

public class SetOfStacks {
	public ArrayList<MyStackInterface<Integer>> stacks;
	private int limit;
	private int counter = 0;
	
	public SetOfStacks(int limit) {
		stacks = new ArrayList<>();
		this.limit = limit;
	}
	
	public void push(Integer element) {
		if(counter % limit == 0) 
			stacks.add(new MySinglyLinkedList<>());
		int index = stacks.size() - 1;
		stacks.get(index).push(element);
		counter++;
	}
	
	public Integer popAt(int index) {
		if(index < 0 || index >= counter)
			System.out.println("Index out of bounds!");
		int listIndex = index / limit;
		MyStackInterface<Integer> tempStack = new MySinglyLinkedList<>();
		int stackIndex = index % limit;
		int i = 0;
		while(i < stackIndex){
			tempStack.push(stacks.get(listIndex).pop());
			i++;
		}
		Integer element = stacks.get(listIndex).pop();
		refillStack(stacks.get(listIndex), tempStack);
		return element;
	}
	private void refillStack(MyStackInterface<Integer> stack, MyStackInterface<Integer> tempStack) {
		while(tempStack.getSize() > 0)
			stack.push(tempStack.pop());
	}

	public Integer pop() {
		int index = stacks.size() - 1;
		Integer element = stacks.get(index).pop();
		if(isStackEmpty(index))
			stacks.remove(index);
		return element;
	}

	private boolean isStackEmpty(int index) {
		return stacks.get(index).getSize() == 0;
	}

	public String toString() {
		String output = "[";
		for (int i = stacks.size() - 1; i >= 0 ; i--) {
			while(stacks.get(i).getSize() > 0)
				output+= stacks.get(i).pop() + ", ";
		}
		return output.substring(0, output.length() - 2) + "]";
	}
}

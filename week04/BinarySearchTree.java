package week04;

import java.util.ArrayList;

public class BinarySearchTree {
	private Node root;
	
	private class Node {
		Integer element;
		Node left;
		Node right;
		
		public Node(Integer element) {
			this.element = element;
		}
	}

	public void insert(Integer element) {
		Node node = new Node(element);
		if(root == null)
			root = node;
		else
			add(root, element);
	}

	private void add(Node node, Integer element) {
		if(element.compareTo(node.element) > 0) {
			if(node.right == null)
				node.right = new Node(element);
			else
				add(node.right, element);
		} else {
			if(node.left == null)
				node.left = new Node(element);
			else
				add(node.left, element);
		}
	}

	public void dfsPrint() {
		printChildren(root);
	}

	private void printChildren(Node node) {
		if(node.left != null)
			printChildren(node.left);
		System.out.println(node.element);
		if(node.right != null)
			printChildren(node.right);
	}
	
	public Integer[] sortArray(Integer[] arr) {
		ArrayList<Integer> tempList = new ArrayList<>();
		tempList = sort(tempList, root); 
		return tempList.toArray(new Integer[tempList.size()]);
	}
	
	private ArrayList<Integer> sort(ArrayList<Integer> tempArr, Node node) {
		if(node.left != null)
			sort(tempArr, node.left);
		tempArr.add(node.element);
		if(node.right != null)
			sort(tempArr, node.right);
		return tempArr;
	}

	public boolean equal(BinarySearchTree otherTree) {
		return compare(root, otherTree.root);
	}

	private boolean compare(Node node, Node nodeOtherTree) {
		if(!node.element.equals(nodeOtherTree.element)) {
			return false;
		}
		
		boolean nodesLeftEqual = (node.left != null && nodeOtherTree.left != null) ||
				(node.left == null && nodeOtherTree.left == null);
		boolean nodesRightEqual = (node.right != null && nodeOtherTree.right != null) ||
				(node.right == null && nodeOtherTree.right == null);
		boolean subTLeftEqual = false;
		boolean subTRightEqual = false;
		if(nodesLeftEqual) {
			if(node.left == null)
				return nodesLeftEqual && nodesRightEqual;
			subTLeftEqual = compare(node.left, nodeOtherTree.left);
		}
		if(nodesRightEqual) {
			if(node.right == null)
				return nodesLeftEqual && nodesRightEqual;
			subTRightEqual = compare(node.right, nodeOtherTree.right);
		}
		return subTLeftEqual && subTRightEqual;
	}
}
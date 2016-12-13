package week6;

import java.util.ArrayList;
import java.util.List;

public class SortedArrayToBalancedTree {

	private static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
		
		public static Node fillTree(List<Integer> values) {
			if(values.isEmpty())
				return null;
			int middle = values.size() / 2;
			int value = values.get(middle);
			Node left = fillTree(values.subList(0, middle));
			Node right = fillTree(values.subList(middle + 1, values.size()));
			return new Node(value, left, right);
		}
		
		public void print(String prefix) {
			if(left != null)
				left.print(prefix + " ");
			System.out.print(prefix);
			System.out.println(value);
			if(right != null)
				right.print(prefix + " ");
		}
		
		public static int size(Node node) {
			if(node == null)
				return 0;
			return 1 + size(node.left) + size(node.right);
		}
		
		public static int depth(Node node) {
			if(node == null)
				return 0;
			int depth =  1 + Math.max(depth(node.left), depth(node.right));
			return depth;
		}
		
		public int size() {
			int size = 1;
			if(left != null)
				size += left.size();
			if(right != null)
				size += right.size();
			return size;
		}
		
		//TODO
		public static int getVetricesCount(Node node) {
			return countVertices(node, 0);
		}
		
		private static int countVertices(Node node, int startN) {
			int n = startN;
			if(node.left != null) 
				n = countVertices(node.left, ++startN);
			
			if(node.right != null)
				n += countVertices(node.right, ++startN);
			return n;
		}
		// END TODO
		
	}
	
	public static void main(String[] args) {
		List<Integer> list = fillNumbers();
		Node root = Node.fillTree(list);
		//root.print("");
		System.out.println(Node.size(root));
		System.out.println(Node.depth(root));
	}

	private static List<Integer> fillNumbers() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(4);
		list.add(6);
		list.add(7);
		list.add(10);
		list.add(12);
		list.add(15);
		list.add(20);
		list.add(23);
		return list;
	}

}

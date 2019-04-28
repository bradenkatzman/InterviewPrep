// Implement in-order traversal for a binary tree. Print the value at each node

// in-order: left-->root-->right
import java.util.*;

public class Node {
	
	private double value;
	private Node left;
	private Node right;
	private boolean visited;

	public Node(double value) {
		this.value = value;
		this.visited = false;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}

	public double getValue() {
		return this.value;
	}

	public boolean isVisited() {
		return this.visited;
	}

	// RIGHT->ROOT->LEFT
	public static void inOrderTraversal(Node root) {
		if (root == null) return;

		inOrderTraversal(root.getLeft());
		System.out.println(root.getValue());
		inOrderTraversal(root.getRight());
	}

	// ROOT->LEFT->RIGHT
	public static void preOrderTraversal(Node root) {
		if (root == null) return;

		System.out.println(root.getValue());
		preOrderTraversal(root.getLeft());
		preOrderTraversal(root.getRight());
	}

	// LEFT->RIGHT->ROOT
	public static void postOrderTraversal(Node root) {
		if (root == null) return;

		postOrderTraversal(root.getLeft());
		postOrderTraversal(root.getRight());
		System.out.println(root.getValue());
	}

	// DFS with in order traversal
	public static void DFS(Node root, double searchValue) {
		if (root == null) return;

		DFS(root.getLeft(), searchValue);
		if (root.getValue() == searchValue) {
			System.out.println("Found value: " + root.getValue());
		} else {
			System.out.println("Didn't find value: " + root.getValue());
		}
		DFS(root.getRight(), searchValue);
	}

	public static void BFS(Node root, double searchValue) {
		// check if the root is null
		if (root == null) return;

		if (root.getValue() == searchValue) {
			System.out.println("Found value at root: " + root.getValue());
		}
		root.setVisited(true);

		// set up a queue to add unvisited nodes to
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		// while there are still nodes to be processed
		while (!queue.isEmpty()) {
			// dequeue the node at the front of the queue
			Node n = queue.remove();
			System.out.println("Just dequeued: " + n.getValue());

			// look through all of n's adjacencies - in a BT, just left and right
			if (n.getLeft() != null && n.getLeft().isVisited() == false) {
				if (n.getLeft().getValue() == searchValue) {
					System.out.println("Found value: " + n.getLeft().getValue());
					return;
				} else {
					// mark n.getLeft as visited and add it to the queue
					n.getLeft().setVisited(true);
					System.out.println("Adding " + n.getLeft().getValue() + " to queue");
					queue.add(n.getLeft());
				}
			}

			if (n.getRight() != null && n.getRight().isVisited() == false) {
				if (n.getRight().getValue() == searchValue) {
					System.out.println("Found value: " + n.getRight().getValue());
					return;
				} else {
					// mark n.getLeft as visited and add it to the queue
					n.getRight().setVisited(true);
					System.out.println("Adding " + n.getRight().getValue() + " to queue");
					queue.add(n.getRight());
				}
			}
		}
		System.out.println("Search value not found");
	}

	public static int computeBTDiameter(Node root) {
		if (root == null) return 0;

		return maxDepth(root.getLeft()) + maxDepth(root.getRight()) + 1;
	}

	public static int maxDepth(Node root) {
		if (root == null) return 0;

		int leftDepth = maxDepth(root.getLeft());
		int rightDepth = maxDepth(root.getRight());

		return 1 + Math.max(leftDepth, rightDepth);
	}

		public static void detectCycleDFS(Node root) {
		if (root == null) return;

		// store the visited nodes so we can easily show the cycle
		List<Node> visitedNodes = new ArrayList<>();

		// perform DFS on graph and stop and print cycle if a node is visited twice
		detectCycleDFS(root, visitedNodes);
	}

	private static void detectCycleDFS(Node root, List<Node> visitedNodes) {
		// base case is to return when the root is null
		if (root == null) return;

		// recursive step - DFS on left substree
		detectCycleDFS(root.getLeft(), visitedNodes);

		// process node by checking if it's already visited
		if (visitedNodes.contains(root)) {
			// cycle detected -- add the node to the list and pass to the print cycle routine
			visitedNodes.add(root);
			printCycle(visitedNodes);
			return;
		} else {
			// add the node
			visitedNodes.add(root);
		}

		// recurse on the right subtree
		detectCycleDFS(root.getRight(), visitedNodes);

	}

	private static void printCycle(List<Node> visitedNodes) {
		// printCycle works by print the nodes in reverse order until the second
		// occurence of the node at the back of the list
		int lastNodeIdx = visitedNodes.size()-1;
		if (lastNodeIdx < 1) return;

		System.out.print("Cycle detected: " + visitedNodes.get(lastNodeIdx).getValue() + " - ");
		for (int i = lastNodeIdx-1; i >= 0; i--) {
			// print the node's value
			System.out.print(visitedNodes.get(i).getValue());

			// check if this node matches the last i.e. see if the whole cycle has been printed
			if (visitedNodes.get(i).equals(visitedNodes.get(lastNodeIdx))) {
				return;
			} else {
				// print separators
				System.out.print(" - ");
			}
		}
	}

	// Time: O(v + e)
	// Space: O(v)
	public static void detectCycleBFS(Node root) {
		if (root == null) return;

		Queue<Node> queue = new LinkedList<>();

		root.setVisited(true);

		// add root to the queue so its adjacencies will be processed
		queue.add(root);

		while (!queue.isEmpty()) {
			Node n = queue.remove();


			// in a graph we'd look through all adjacencies, here we know its a BT so just look at each child
			if (n.getLeft() != null) {
				if (n.getLeft().isVisited()) {
					System.out.println("Cycle detected processing node with value: " + n.getValue());
					return;
				}
				
				// mark as visited and add to queue
				n.getLeft().setVisited(true);
				queue.add(n.getLeft());
			}
			
			if (n.getRight() != null) {
				if (n.getRight().isVisited()) {
					System.out.println("Cycle detected processing node with value: " + n.getValue());
					return;
				}

				// mark as visited and add to queue
				n.getRight().setVisited(true);
				queue.add(n.getRight());	
			}
		}
	}

	public static void main(String[] args) {
		// Create a binary tree
		Node root = new Node(5);
		root.setLeft(new Node(3));
		root.getLeft().setLeft(new Node(2));
		root.getLeft().setRight(new Node(4));

		root.setRight(new Node(7));
		root.getRight().setLeft(new Node(6));
		root.getRight().setRight(new Node(8));

		//				5
		//	   	     /     \
		//.         3	     7
		//		  /   \    /  \
		//		 2     4  6    8

		System.out.println("In order traversal:");
		inOrderTraversal(root);
		System.out.println("");

		System.out.println("Pre order traversal:");
		preOrderTraversal(root);
		System.out.println("");

		System.out.println("Post order traversal:");
		postOrderTraversal(root);
		System.out.println("");

		System.out.println("DFS for 7:");
		DFS(root, 7);
		System.out.println("");

		System.out.println("BFS for 7");
		BFS(root, 8);
		System.out.println("");

		System.out.println("Diameter of tree:");
		int diameter = computeBTDiameter(root);
		System.out.println("Diameter = " + diameter);
		System.out.println("");

		// System.out.println("Paths in tree with sum");
		// printAllPathsEqualingSum(root, 5);
		// System.out.println("");

		Node root2 = new Node(5);
		Node cycleNode = new Node(3);
		root2.setLeft(cycleNode);
		root2.getLeft().setLeft(new Node(2));
		root2.getLeft().setRight(new Node(4));

		root2.setRight(new Node(7));
		root2.getRight().setLeft(new Node(6));
		root2.getRight().setRight(new Node(8));
		root2.getRight().getRight().setLeft(cycleNode);
		System.out.println("Detect cycle DFS: ");
		detectCycleDFS(root2);

		System.out.println("Detect cycle BFS: ");
		detectCycleBFS(root2);
	}

}


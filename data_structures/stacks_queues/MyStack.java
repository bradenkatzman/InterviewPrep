// implement a stack with the following operations:
// 1. push
// 2. pop
// 3. peek
// 4. isEmpty()
import java.util.*;

public class MyStack <Object> {
	private ArrayList<Object> stack; // the underlying data structure for the stack implementation is an ArrayList


	public MyStack() {
	 	this.stack = new ArrayList<Object>();
	}

	public void push(Object data) {
		if (this.stack != null) {
			stack.add(data); // the add operation on an ArrayList adds items to the end of the list
		}
	}

	public Object pop() {
		if (this.stack != null) {
			return this.stack.remove(this.stack.size()-1); // remove operation returns the element that was removed from the list
		}
		return null;
	}

	public Object peek() {
		if (this.stack != null) {
			return this.stack.get(this.stack.size()-1); // access the 'top' item without popping it
		}
		return null;
	}

	public boolean isEmpty() {
		if (this.stack != null) {
			return this.stack.isEmpty();
		}
		return true;
	}

	public static void main (String[] args) {
		MyStack stack = new MyStack<Integer>();
		stack.push(1);
		stack.push(5);
		
		System.out.println(stack.pop());
	}
}


// implement a queue with the following operations:
// 1. enqueue
// 2. dequeue

//public class Queue {
//	private ArrayList<Object> 
//}


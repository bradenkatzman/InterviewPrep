import java.util.*;

public class MyQueue <Object> {
	private ArrayList<Object> queue;

	public MyQueue() {
		this.queue = new ArrayList<>();
	}

	public void enqueue(Object data) {
		if (this.queue != null) {
			this.queue.add(data);
		}
	}

	public Object dequeue() {
		if (this.queue != null) {
			return this.queue.remove(0);
		}
		return null;
	}

	public static void main(String[] args) {
		MyQueue queue = new MyQueue<Integer>();
		queue.enqueue(1);
		queue.enqueue(5);

		System.out.println(queue.dequeue());
	}
}
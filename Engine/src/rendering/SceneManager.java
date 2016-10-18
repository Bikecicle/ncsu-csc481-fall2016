package rendering;

import java.util.LinkedList;
import java.util.Queue;

public class SceneManager {

	private Queue<Scene> buffer;
	private Scene current;
	
	public SceneManager () {
		buffer = new LinkedList<Scene>();
		current = new Scene();
	}
	
	public synchronized void render(Renderable shape) {
		current.render(shape);
	}
	
	public synchronized void pushCurrent() {
		buffer.add(current);
		current = new Scene();
	}
	
	public synchronized boolean isEmpty(){
		return buffer.isEmpty();
	}
	
	public synchronized Scene read() {
		return buffer.peek();
	}
	
	public synchronized void toss(){
		buffer.poll();
	}
}

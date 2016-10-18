package rendering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scene implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8161957444351432644L;
	private List<Renderable> list;

	public Scene() {
		list = new ArrayList<Renderable>();
	}
	
	public Scene(Scene scene) {
		list = new ArrayList<Renderable>(scene.getList());
	}

	public synchronized void clear() {
		list.clear();
	}

	public synchronized void render(Renderable renderable) {
		list.add(renderable);
	}
	
	public synchronized List<Renderable> getList() {
		return list;
	}
	
	public synchronized String toString() {
		return list.toString();
	}
}

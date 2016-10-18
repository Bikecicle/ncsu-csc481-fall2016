package gameobject;

import java.util.LinkedList;
import java.util.List;

public class World {

	private List<GameObject> worldObjects;
	
	public World(){
		worldObjects = new LinkedList<GameObject>();
	}

	public synchronized void buildGameObject(GameObject gameObject) {
		worldObjects.add(gameObject);
	}

	public synchronized void update() {
		for (GameObject object : worldObjects) {
			object.update();
		}
	}
}

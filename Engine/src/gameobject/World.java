package gameobject;

import java.util.LinkedList;
import java.util.List;

import component.Component;

public class World {

	private List<GameObject> worldObjects;

	public World() {
		worldObjects = new LinkedList<GameObject>();
	}

	public synchronized void buildGameObject(GameObject gameObject) {
		worldObjects.add(gameObject);
	}

	public synchronized boolean destroyGameObject(Component component) {
		for (GameObject object : worldObjects) {
			if (object.contains(component)) {
				worldObjects.remove(object);
				return true;
			}
		}
		return false;
	}

	public synchronized void update() {
		for (GameObject object : worldObjects) {
			object.update();
		}
	}
}

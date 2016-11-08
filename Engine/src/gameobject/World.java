package gameobject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import component.Component;
import event.Event;
import event.EventHandler;
import event.EventManager;
import event.TeleportWorldEvent;
import util.EConstant;

public class World implements EventHandler, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7622794570858148559L;
	private List<GameObject> worldObjects;

	public World(EventManager eventManager) {
		this.worldObjects = new LinkedList<GameObject>();

		eventManager.register(EConstant.TELEPORT_WORLD_EVENT, this);
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

	public synchronized void replace(World world) {
		worldObjects = new LinkedList<GameObject>(world.getWorldObjects());
	}

	public List<GameObject> getWorldObjects() {
		return worldObjects;
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.TELEPORT_WORLD_EVENT) {
			TeleportWorldEvent twEvent = (TeleportWorldEvent) event;
			if (twEvent.getWorld() != this) {
				replace(twEvent.getWorld());
			}
		}
	}
}

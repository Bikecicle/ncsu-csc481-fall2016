package gameobject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import component.Component;
import event.Event;
import event.EventHandler;
import event.EventManager;
import event.StreamWorldEvent;
import util.EConstant;

public class World implements EventHandler, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7622794570858148559L;
	private List<Component> components;
	private List<GameObject> roster;
	private EventManager eventManager;
	private int wid;

	public World(String name, EventManager eventManager) {
		this.components = new LinkedList<Component>();
		this.roster = new LinkedList<GameObject>();
		this.eventManager = eventManager;
		this.wid = name.hashCode();
		register();
	}

	public synchronized void addGameObject(GameObject gameObject) {
		components.addAll(gameObject.build(eventManager));
		roster.add(gameObject);
	}

	public synchronized void addDummyObject(GameObject gameObject) {
		if (gameObject.hasDummy())
			components.addAll(gameObject.buildDummy(eventManager));
		roster.add(gameObject);
	}

	public synchronized void destroyGameObject(int oid) {
		for (Component component : components) {
			if (component.getOid() == oid)
				components.remove(component);
		}
		for (GameObject object : roster) {
			if (object.getOid() == oid) {
				roster.remove(object);
			}
		}
	}

	public synchronized void bind(List<GameObject> masterRoster) {
		roster.clear();
		components.clear();
		for (GameObject object : masterRoster) {
			addDummyObject(object);
		}
		System.out.println("World copied and bound to server");
	}

	public List<Component> getComponents() {
		return components;
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.WORLD_REQUEST_EVENT) {
			eventManager.raise(new StreamWorldEvent(eventManager.getTime(), roster, wid));
		} else if (event.getType() == EConstant.STREAM_WORLD_EVENT) {
			StreamWorldEvent swEvent = (StreamWorldEvent) event;
			if (swEvent.getWid() != wid) {
				bind(swEvent.getRoster());
			}
		}
	}

	@Override
	public void register() {
		eventManager.register(EConstant.WORLD_REQUEST_EVENT, this);
		eventManager.register(EConstant.STREAM_WORLD_EVENT, this);
	}
}

package gameobject;

import java.io.Serializable;
import java.util.ArrayList;
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
	private List<Component> worldComponents;
	private EventManager eventManager;

	public World(EventManager eventManager) {
		this.worldComponents = new LinkedList<Component>();
		this.eventManager = eventManager;
		register();
	}

	public synchronized void addGameObject(List<Component> gameObject) {
		worldComponents.addAll(gameObject);
	}

	public synchronized void destroyGameObject(int oid) {
		for (Component component : worldComponents) {
			if (component.getOid() == oid)
				worldComponents.remove(component);
		}
	}
	
	public synchronized void repopulate( List<Component> newComponents ) {
		worldComponents = new LinkedList<Component>(newComponents);
		for (Component component : worldComponents) {
			component.setEventManager(eventManager);
		}
	}

	public List<Component> getWorldComponents() {
		return worldComponents;
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.WORLD_REQUEST_EVENT) {
			List<Component> componentStream = new ArrayList<Component>(worldComponents);
			for (Component component : componentStream) {
				component.setEventManager(null);
			}
			eventManager.raise(new StreamWorldEvent(eventManager.getTime(), componentStream));
		}
	}

	@Override
	public void register() {
		eventManager.register(EConstant.WORLD_REQUEST_EVENT, this);
	}
}

package component;

import java.io.Serializable;

import event.EventHandler;
import event.EventManager;

public abstract class Component implements EventHandler, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4139325457865841201L;
	protected int guid;
	protected EventManager eventManager;

	public Component(int guid, EventManager eventManager) {
		this.guid = guid;
		this.eventManager = eventManager;
	}

	public int getGuid() {
		return guid;
	}
	
	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager){
		this.eventManager = eventManager;
	}
	
	public abstract Component copy();

}

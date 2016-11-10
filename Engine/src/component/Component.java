package component;

import java.io.Serializable;

import event.EventHandler;
import event.EventManager;

public abstract class Component implements EventHandler, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4139325457865841201L;
	protected int oid;
	protected EventManager eventManager;

	public Component(int oid, EventManager eventManager) {
		this.oid = oid;
		this.eventManager = eventManager;
	}

	public int getOid() {
		return oid;
	}
	
	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager){
		this.eventManager = eventManager;
	}
	
	public abstract Component copy();

}

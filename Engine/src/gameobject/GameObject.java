package gameobject;

import java.io.Serializable;
import java.util.List;

import component.Component;
import event.EventManager;

public abstract class GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6922091393222647907L;
	protected int guid;
	
	public GameObject(String name) {
		this.guid = name.hashCode();
	}
	
	public abstract List<Component> build(EventManager eventManager);
	
	public abstract List<Component> buildDummy(EventManager eventManager);
	
	public abstract boolean hasDummy();
	
	public int getGuid() {
		return guid;
	}
}

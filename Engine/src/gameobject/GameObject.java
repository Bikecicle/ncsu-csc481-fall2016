package gameobject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import component.Component;

public abstract class GameObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6065937386571593950L;
	private List<Component> components;
	
	public GameObject() {
		this.components = new LinkedList<Component>();
	}

	public void addComponent(Component component) {
		components.add(component);
	}
	
	public boolean contains(Component component) {
		return components.contains(component);
	}

}

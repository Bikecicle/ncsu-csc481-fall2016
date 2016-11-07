package gameobject;

import java.util.LinkedList;
import java.util.List;

import component.Component;

public abstract class GameObject {

	private List<Component> components;
	
	public GameObject() {
		this.components = new LinkedList<Component>();
	}

	public void addComponent(Component component) {
		components.add(component);
	}
	
	public void update() {
		for (Component component : components) {
			component.update();
		}
	}
	
	public boolean contains(Component component) {
		return components.contains(component);
	}

}

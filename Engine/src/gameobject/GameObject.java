package gameobject;

import java.util.LinkedList;
import java.util.List;

import gameobjectcomponent.Component;

public class GameObject {

	private List<Component> components;
	private World world;

	public GameObject(World world) {
		this.components = new LinkedList<Component>();
		this.world = world;
	}

	public void addComponent(Component component) {
		components.add(component);
	}
	
	public World getWorld() {
		return world;
	}

}

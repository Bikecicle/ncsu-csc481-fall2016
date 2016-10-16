package game_object_model.object;

import java.util.LinkedList;
import java.util.List;

import game_object_model.component.Component;

public class GameObject {
	
	private List<Component> components;
	
	public GameObject() {
		components = new LinkedList<Component>();
	}
	
	public void addComponent( Component component){
		components.add(component);
	}
	
}

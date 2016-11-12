package gameobject;

import java.util.LinkedList;
import java.util.List;

import component.Component;
import component.SpawnPointComponent;
import component.WorldPositionComponent;
import event.EventManager;

public class SpawnPoint extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 39528746024392311L;
	private double x;
	private double y;

	public SpawnPoint(String name, double x, double y) {
		super(name);
		this.x = x;
		this.y = y;
	}

	@Override
	public List<Component> build(EventManager eventManager) {
		WorldPositionComponent position = new WorldPositionComponent(guid, eventManager, x, y);
		SpawnPointComponent spawner = new SpawnPointComponent(guid, eventManager, position);

		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(spawner);
		return gameObject;
	}

	@Override
	public List<Component> buildDummy(EventManager eventManager) {
		return null;
	}

	@Override
	public boolean hasDummy() {
		return false;
	}
}

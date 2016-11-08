package gameobject;

import component.SpawnPointComponent;
import component.WorldPositionComponent;
import event.EventManager;

public class SpawnPoint extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1631831045746253917L;

	public SpawnPoint(EventManager eventManager, double x, double y) {
		super();
		WorldPositionComponent position = new WorldPositionComponent(eventManager, x, y);
		SpawnPointComponent spawner = new SpawnPointComponent(eventManager, position);
				
		addComponent(position);
		addComponent(spawner);
	}
}

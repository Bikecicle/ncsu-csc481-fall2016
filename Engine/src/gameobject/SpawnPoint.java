package gameobject;

import component.SpawnPointComponent;
import component.WorldPositionComponent;
import event.EventManager;

public class SpawnPoint extends GameObject {

	public SpawnPoint(EventManager eventManager, double x, double y) {
		super();
		WorldPositionComponent position = new WorldPositionComponent(x, y);
		SpawnPointComponent spawner = new SpawnPointComponent(eventManager, position);
				
		addComponent(position);
		addComponent(spawner);
	}
}

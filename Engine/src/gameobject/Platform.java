package gameobject;

import component.CollisionBoxComponent;
import component.ColoredRectangleComponent;
import component.WorldPositionComponent;
import event.EventManager;
import rendering.Scene;
import rendering.SceneManager;

public class Platform extends GameObject {

	public Platform( SceneManager sceneManager, EventManager eventManager, int x, int y, int width, int height ) {
		super();
		WorldPositionComponent position = new WorldPositionComponent(x, y);
		CollisionBoxComponent hitBox = new CollisionBoxComponent(eventManager, position, width, height, false);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(sceneManager, eventManager, position, width, height);
		
		addComponent(position);
		addComponent(hitBox);
		addComponent(renderShape);
	}
}

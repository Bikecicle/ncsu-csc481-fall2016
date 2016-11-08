package gameobject;

import component.CollisionBoxComponent;
import component.ColoredRectangleComponent;
import component.WorldPositionComponent;
import event.EventManager;
import rendering.SceneManager;

public class Platform extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3881068750626143151L;

	public Platform( SceneManager sceneManager, EventManager eventManager, int x, int y, int width, int height ) {
		super();
		WorldPositionComponent position = new WorldPositionComponent(eventManager, x, y);
		CollisionBoxComponent hitBox = new CollisionBoxComponent(eventManager, position, width, height, true, false);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(sceneManager, eventManager, position, width, height);
		
		addComponent(position);
		addComponent(hitBox);
		addComponent(renderShape);
	}
}

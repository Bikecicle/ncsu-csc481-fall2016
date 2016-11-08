package gameobject;

import component.ChaoticMotionComponent;
import component.CollisionBoxComponent;
import component.ColoredRectangleComponent;
import component.MovementComponent;
import component.WorldPositionComponent;
import event.EventManager;
import rendering.SceneManager;

public class MovingPlatform extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5612477317948857182L;

	public MovingPlatform(SceneManager sceneManager, EventManager eventManager, double x, double y, int width, int height) {
		super();
		WorldPositionComponent position = new WorldPositionComponent(eventManager, x, y);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(eventManager, position, width, height, true, false);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(sceneManager, eventManager, position,
				width, height);
		ChaoticMotionComponent chaos = new ChaoticMotionComponent(x, y);
		MovementComponent movement = new MovementComponent(eventManager, hitbox, chaos);

		addComponent(position);
		addComponent(hitbox);
		addComponent(renderShape);
		addComponent(chaos);
		addComponent(movement);
	}
}

package gameobject;

import component.ChaoticMotionComponent;
import component.CollisionBoxComponent;
import component.ColoredRectangleComponent;
import component.MovementComponent;
import component.WorldPositionComponent;
import event.EventManager;
import rendering.SceneManager;

public class MovingPlatform extends GameObject {

	public MovingPlatform(SceneManager sceneManager, EventManager eventManager, int x, int y, int width, int height) {
		super();
		WorldPositionComponent position = new WorldPositionComponent(x, y);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(eventManager, position, width, height, true, false);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(sceneManager, eventManager, position,
				width, height);
		ChaoticMotionComponent chaos = new ChaoticMotionComponent(x, y);
		MovementComponent movement = new MovementComponent(eventManager, position, hitbox, chaos);

		addComponent(position);
		addComponent(hitbox);
		addComponent(renderShape);
		addComponent(chaos);
		addComponent(movement);
	}
}

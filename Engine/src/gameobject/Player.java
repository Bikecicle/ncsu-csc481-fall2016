package gameobject;

import component.CollisionBoxComponent;
import component.MovementComponent;
import component.PhysicsComponent;
import component.PlayerControlComponent;
import component.ColoredRectangleComponent;
import component.WorldPositionComponent;
import event.EventManager;
import rendering.Scene;
import rendering.SceneManager;
import util.EConstant;

public class Player extends GameObject {

	public Player(SceneManager sceneManager, EventManager eventManager, int playerId) {
		super();
		WorldPositionComponent position = new WorldPositionComponent();
		CollisionBoxComponent hitbox = new CollisionBoxComponent(eventManager, position, EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT, true);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(sceneManager, eventManager, position, EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT);
		PlayerControlComponent input = new PlayerControlComponent(eventManager, hitbox, playerId);
		PhysicsComponent physics = new PhysicsComponent(eventManager);
		MovementComponent movement = new MovementComponent(eventManager, position, hitbox, physics, input);
			
				
		addComponent(position);
		addComponent(hitbox);
		addComponent(renderShape);
		addComponent(physics);
		addComponent(input);
		addComponent(movement);
	}
}

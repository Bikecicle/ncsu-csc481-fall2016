package gameobject;

import component.CollisionBoxComponent;
import component.MovementComponent;
import component.PhysicsComponent;
import component.GravityComponent;
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
		GravityComponent gravity = new GravityComponent();
		MovementComponent movement = new MovementComponent(eventManager, position, hitbox, gravity, input);
		PhysicsComponent physics = new PhysicsComponent(eventManager, movement);
			
				
		addComponent(position);
		addComponent(hitbox);
		addComponent(renderShape);
		addComponent(gravity);
		addComponent(input);
		addComponent(movement);
	}
}

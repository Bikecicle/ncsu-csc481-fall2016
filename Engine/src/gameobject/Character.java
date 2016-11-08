package gameobject;

import component.CollisionBoxComponent;
import component.MovementComponent;
import component.CollisionPhysicsComponent;
import component.GravityComponent;
import component.PlayerControlComponent;
import component.RespawnComponent;
import component.ColoredRectangleComponent;
import component.WorldPositionComponent;
import event.EventManager;
import rendering.SceneManager;
import util.EConstant;

public class Character extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1043232090456961148L;

	public Character(SceneManager sceneManager, EventManager eventManager, int playerId) {
		super();
		WorldPositionComponent position = new WorldPositionComponent(eventManager, 50, 50);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(eventManager, position, EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT, true, true);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(sceneManager, eventManager, position, EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT);
		PlayerControlComponent input = new PlayerControlComponent(eventManager, hitbox, playerId);
		GravityComponent gravity = new GravityComponent();
		MovementComponent movement = new MovementComponent(eventManager, hitbox, gravity, input);
		CollisionPhysicsComponent physics = new CollisionPhysicsComponent(eventManager, movement);
		RespawnComponent respawn = new RespawnComponent(eventManager, hitbox);
				
		addComponent(position);
		addComponent(hitbox);
		addComponent(renderShape);
		addComponent(gravity);
		addComponent(input);
		addComponent(movement);
		addComponent(physics);
		addComponent(respawn);
	}
}

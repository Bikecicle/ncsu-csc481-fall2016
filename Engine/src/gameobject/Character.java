package gameobject;

import java.util.LinkedList;
import java.util.List;

import component.CollisionBoxComponent;
import component.MovementComponent;
import component.CollisionPhysicsComponent;
import component.GravityComponent;
import component.PlayerControlComponent;
import component.RespawnComponent;
import component.ColoredRectangleComponent;
import component.Component;
import component.WorldPositionComponent;
import event.EventManager;
import util.EConstant;

public class Character extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2633122954777485159L;
	private int playerId;

	public Character(String name, int playerId) {
		super(name);
		this.playerId = playerId;
	}

	@Override
	public List<Component> build(EventManager eventManager) {
		WorldPositionComponent position = new WorldPositionComponent(guid, eventManager, 50, 50);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(guid, eventManager, position, EConstant.PLAYER_WIDTH,
				EConstant.PLAYER_HEIGHT, true, true);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(guid, eventManager, position,
				EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT);
		PlayerControlComponent input = new PlayerControlComponent(guid, eventManager, playerId);
		GravityComponent gravity = new GravityComponent(guid, eventManager);
		MovementComponent movement = new MovementComponent(guid, eventManager, hitbox, gravity, input);
		CollisionPhysicsComponent physics = new CollisionPhysicsComponent(guid, eventManager, movement);
		RespawnComponent respawn = new RespawnComponent(guid, eventManager, hitbox);

		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(hitbox);
		gameObject.add(renderShape);
		gameObject.add(gravity);
		gameObject.add(input);
		gameObject.add(movement);
		gameObject.add(physics);
		gameObject.add(respawn);
		return gameObject;
	}

	@Override
	public List<Component> buildDummy(EventManager eventManager) {
		WorldPositionComponent position = new WorldPositionComponent(guid, eventManager, 50, 50);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(guid, eventManager, position,
				EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT);
		
		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(renderShape);
		return gameObject;
	}

	@Override
	public boolean hasDummy() {
		return true;
	}
}

package gameobject;

import java.util.LinkedList;
import java.util.List;

import component.CollisionBoxComponent;
import component.MovementComponent;
import component.PlayerControlComponent;
import component.RespawnComponent;
import component.ColoredRectangleComponent;
import component.Component;
import component.CustomDamageComponent;
import component.WorldPositionComponent;
import event.EventManager;
import event.RespawnEvent;
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
				EConstant.PLAYER_HEIGHT, true, false);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(guid, eventManager, position,
				EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT);
		PlayerControlComponent input = new PlayerControlComponent(guid, eventManager, playerId);
		CustomDamageComponent damage = new CustomDamageComponent(guid, eventManager, "destroy_on_impact.js");
		MovementComponent movement = new MovementComponent(guid, eventManager, hitbox, input);
		RespawnComponent respawn = new RespawnComponent(guid, eventManager, hitbox);

		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(hitbox);
		gameObject.add(renderShape);
		gameObject.add(input);
		gameObject.add(damage);
		gameObject.add(movement);
		gameObject.add(respawn);
		eventManager.raise(new RespawnEvent(eventManager.getTime(), guid, EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT));
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

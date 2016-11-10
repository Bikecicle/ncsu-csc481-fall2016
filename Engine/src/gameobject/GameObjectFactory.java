package gameobject;

import java.util.LinkedList;
import java.util.List;

import component.ChaoticMotionComponent;
import component.CollisionBoxComponent;
import component.CollisionDamageComponent;
import component.CollisionPhysicsComponent;
import component.ColoredRectangleComponent;
import component.Component;
import component.GravityComponent;
import component.MovementComponent;
import component.PlayerControlComponent;
import component.RespawnComponent;
import component.SpawnPointComponent;
import component.WorldPositionComponent;
import event.EventManager;
import rendering.Scene;
import util.EConstant;

public class GameObjectFactory {

	private EventManager eventManager;
	private Scene scene;
	
	public GameObjectFactory(EventManager eventManager, Scene scene) {
		this.eventManager = eventManager;
		this.scene = scene;
	}
	
	public List<Component> character(String name, int playerId) {
		int id = name.hashCode();
		WorldPositionComponent position = new WorldPositionComponent(id, eventManager, 50, 50);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(id, eventManager, position, EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT, true, true);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(id, eventManager, scene, position, EConstant.PLAYER_WIDTH, EConstant.PLAYER_HEIGHT);
		PlayerControlComponent input = new PlayerControlComponent(id, eventManager, playerId);
		GravityComponent gravity = new GravityComponent(id, eventManager);
		MovementComponent movement = new MovementComponent(id, eventManager, hitbox, gravity, input);
		CollisionPhysicsComponent physics = new CollisionPhysicsComponent(id, eventManager, movement);
		RespawnComponent respawn = new RespawnComponent(id, eventManager, hitbox);
		
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
	
	public List<Component> deathZone(String name, double x, double y, double width, double height) {
		int id = name.hashCode();
		WorldPositionComponent position = new WorldPositionComponent(id, eventManager, x, y);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(id, eventManager, position, width, height, false, false);
		CollisionDamageComponent hazard = new CollisionDamageComponent(id, eventManager, hitbox);
		
		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(hitbox);
		gameObject.add(hazard);
		return gameObject;
	}
	
	public List<Component> movingPlatform(String name, double x, double y, int width, int height) {
		int id = name.hashCode();
		WorldPositionComponent position = new WorldPositionComponent(id, eventManager, x, y);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(id, eventManager, position, width, height, true, false);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(id, eventManager, scene, position,
				width, height);
		ChaoticMotionComponent chaos = new ChaoticMotionComponent(id, eventManager, x, y);
		MovementComponent movement = new MovementComponent(id, eventManager, hitbox, chaos);

		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(hitbox);
		gameObject.add(renderShape);
		gameObject.add(chaos);
		gameObject.add(movement);
		return gameObject;
	}
	

	public List<Component> platform(String name, int x, int y, int width, int height ) {
		int id = name.hashCode();
		WorldPositionComponent position = new WorldPositionComponent(id, eventManager, x, y);
		CollisionBoxComponent hitBox = new CollisionBoxComponent(id, eventManager, position, width, height, true, false);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(id, eventManager, scene, position, width, height);
		
		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(hitBox);
		gameObject.add(renderShape);
		return gameObject;
	}
	
	public List<Component> spawnPoint(String name, double x, double y) {
		int id = name.hashCode();
		WorldPositionComponent position = new WorldPositionComponent(id, eventManager, x, y);
		SpawnPointComponent spawner = new SpawnPointComponent(id, eventManager, position);
		
		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(spawner);
		return gameObject;
	}
}

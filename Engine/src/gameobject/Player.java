package gameobject;

import gameobjectcomponent.CircleProfileComponent;
import gameobjectcomponent.CollisionBoxComponent;
import gameobjectcomponent.PlayerInputComponent;
import gameobjectcomponent.WorldPositionComponent;
import util.EngineConstants;

public class Player extends GameObject {

	public Player(World world) {
		super(world);
		WorldPositionComponent position = new WorldPositionComponent();
		CollisionBoxComponent hitbox = new CollisionBoxComponent(position, EngineConstants.PLAYER_WIDTH, EngineConstants.PLAYER_HEIGHT);
		CircleProfileComponent renderShape = new CircleProfileComponent(position);
		PlayerInputComponent input = new PlayerInputComponent();
		//PhysicsComponent physics = new PhysicsComponent(position);	
	}

}

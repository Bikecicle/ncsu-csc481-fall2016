package gameobject;

import component.CollisionBoxComponent;
import component.CollisionDamageComponent;
import component.WorldPositionComponent;
import event.EventManager;

public class DeathZone extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2359665203006803786L;

	public DeathZone(EventManager eventManager, double x, double y, double width, double height) {
		WorldPositionComponent position = new WorldPositionComponent(eventManager, x, y);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(eventManager, position, width, height, false, false);
		CollisionDamageComponent hazard = new CollisionDamageComponent(eventManager, hitbox);
		
		addComponent(position);
		addComponent(hitbox);
		addComponent(hazard);
	}

}

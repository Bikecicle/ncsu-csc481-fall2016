package event;

import component.CollisionBoxComponent;
import component.WorldPositionComponent;
import util.EConstant;

public class ObjectMovedEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5063568448250232841L;
	private CollisionBoxComponent hitbox;
	private double positionX, positionY;

	public ObjectMovedEvent(long timestamp, CollisionBoxComponent hitbox, double positionX, double positionY) {
		super(timestamp, EConstant.OBJECT_MOVED_EVENT, EConstant.PRIORITY_MOVEMENT);
		this.hitbox = hitbox;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public CollisionBoxComponent getHitbox() {
		return hitbox;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}
}

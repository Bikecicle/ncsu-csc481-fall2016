package event;

import component.CollisionBoxComponent;
import util.EConstant;

public class CollisionEvent extends Event {

	private CollisionBoxComponent hitbox1, hitbox2;
	private double overlapX, overlapY;

	public CollisionEvent(CollisionBoxComponent hitbox1, CollisionBoxComponent hitbox2, double overlapX, double overlapY) {
		super(EConstant.COLLISION_EVENT, EConstant.PRIORITY_PHYSICS);
		this.hitbox1 = hitbox1;
		this.hitbox2 = hitbox2;
		this.overlapX = overlapX;
		this.overlapY = overlapY;
	}

	public CollisionBoxComponent getHitbox1() {
		return hitbox1;
	}
	
	public CollisionBoxComponent getHitbox2() {
		return hitbox2;
	}
	
	public double getOverlapX() {
		return overlapX;
	}

	public double getOverlapY() {
		return overlapY;
	}

	public boolean isCollidingFromBelow() {
		return overlapY > 0 && Math.abs(overlapY) < Math.abs(overlapX);
	}
	
	public boolean isSolid() {
		return hitbox2.isSolid();
	}
	
	public boolean isMoveable() {
		return hitbox2.isMoveable();
	}
}

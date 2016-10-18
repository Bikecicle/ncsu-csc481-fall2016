package event;

import component.CollisionBoxComponent;
import component.MovementComponent;
import util.EConstant;

public class CollisionEvent extends Event {
	
	public MovementComponent movementComponent;
	public CollisionBoxComponent collisionBoxComponent;

	public CollisionEvent(MovementComponent movementComponent, CollisionBoxComponent collisionBoxComponent) {
		super(EConstant.COLLISION_EVENT, EConstant.PRIORITY_HIGH);
		this.movementComponent = movementComponent;
		this.collisionBoxComponent = collisionBoxComponent;
	}
	
	public MovementComponent getMovementComponent() {
		return movementComponent;
	}
	
	public CollisionBoxComponent getCollisionBoxComponent() {
		return collisionBoxComponent;
	}

}

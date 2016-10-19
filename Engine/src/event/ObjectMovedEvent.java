package event;

import component.MovementComponent;
import util.EConstant;

public class ObjectMovedEvent extends Event {
	
	private MovementComponent movementComponent;

	public ObjectMovedEvent( MovementComponent movementComponent ) {
		super(EConstant.OBJECT_MOVED_EVENT, EConstant.PRIORITY_MEDIUM);
		this.movementComponent = movementComponent;
	}
	
	public MovementComponent getMovementComponent() {
		return movementComponent;
	}
}

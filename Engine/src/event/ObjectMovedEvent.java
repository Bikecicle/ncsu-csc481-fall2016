package event;

import component.MovementComponent;
import util.EConstant;

public class ObjectMovedEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5063568448250232841L;
	private MovementComponent movementComponent;

	public ObjectMovedEvent(Long timestamp, MovementComponent movementComponent) {
		super(timestamp, EConstant.OBJECT_MOVED_EVENT, EConstant.PRIORITY_MOVEMENT);
		this.movementComponent = movementComponent;
	}

	public MovementComponent getMovementComponent() {
		return movementComponent;
	}
}

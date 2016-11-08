package component;

import event.Event;
import util.EConstant;

public class GravityComponent implements Component, Driver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2370007356638300795L;

	public GravityComponent() {
		
	}

	@Override
	public void onEvent(Event event) {
		
	}

	@Override
	public void drive(MovementComponent movementComponent) {
		movementComponent.setVelocityY(movementComponent.getVelocityY() + EConstant.GRAVITY * movementComponent.getDt());
	}

}

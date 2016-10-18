package component;

import event.Event;
import util.EConstant;

public class GravityComponent implements Component, Driver {
	
	public GravityComponent() {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onEvent(Event event) {
		
	}

	@Override
	public void drive(MovementComponent movementComponent) {
		movementComponent.setVelocityY(movementComponent.getVelocityY() + EConstant.GRAVITY);
	}

}

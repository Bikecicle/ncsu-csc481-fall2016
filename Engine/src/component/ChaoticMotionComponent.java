package component;

import event.Event;

public class ChaoticMotionComponent implements Component, Driver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5254311684821470068L;
	double x, y;
	double t = 0;
	double a = 10;

	public ChaoticMotionComponent(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void drive(MovementComponent movementComponent) {
		t += movementComponent.getDt();
		movementComponent.setTempPositionX(x + a * Math.cos(0.5 * t));
		movementComponent.setTempPositionY(y + a * Math.cos(0.3 * t));
	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub

	}

}

package component;

import event.Event;

public class ChaoticMotionComponent implements Component, Driver {

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
		movementComponent.getPosition().setPositionX(x + a*Math.cos(0.5*t));
		movementComponent.getPosition().setPositionY(y + a*Math.cos(0.3*t));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub

	}

}

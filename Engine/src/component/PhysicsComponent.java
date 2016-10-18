package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import util.EConstant;

public class PhysicsComponent implements Component, Driver {

	private EventManager eventManager;
	
	public PhysicsComponent(EventManager eventManager) {
		this.eventManager = eventManager;
		
		eventManager.register(EConstant.COLLISION_EVENT, this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onEvent(Event event) {
		MovementComponent mc = ((CollisionEvent) event).getMovementComponent();
		
	}

	@Override
	public void drive(MovementComponent movementComponent) {
		movementComponent.setVelocityY(movementComponent.getVelocityY() + EConstant.GRAVITY);
	}

}

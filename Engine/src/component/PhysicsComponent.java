package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import util.EConstant;

public class PhysicsComponent implements Component {

	EventManager eventManager;
	MovementComponent movementComponent;

	public PhysicsComponent(EventManager eventManager, MovementComponent movementComponent) {
		this.eventManager = eventManager;
		this.movementComponent = movementComponent;

		eventManager.register(EConstant.COLLISION_EVENT, this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEvent(Event event) {
		if (((CollisionEvent) event).getMovementComponent() == movementComponent) {
			MovementComponent mc = ((CollisionEvent) event).getMovementComponent();
			CollisionBoxComponent cb = ((CollisionEvent) event).getCollisionBoxComponent();
			double overlapX;
			double overlapY;
			if (mc.getVelocityX() >= 0) {
				overlapX = (mc.getPosition().getX() + mc.getHitbox().getWidth() / 2)
						- (cb.getPosition().getX() - cb.getWidth() / 2);
			} else {
				overlapX = (mc.getPosition().getX() - mc.getHitbox().getWidth() / 2)
						- (cb.getPosition().getX() + cb.getWidth() / 2);

			}
		}
	}

}

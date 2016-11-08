package component;

import event.CollisionEvent;
import event.MomentumTransferEvent;
import event.ObjectMovedEvent;
import event.Event;
import event.EventManager;
import util.EConstant;

public class CollisionPhysicsComponent implements Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1550687788305067996L;
	EventManager eventManager;
	MovementComponent movementComponent;

	public CollisionPhysicsComponent(EventManager eventManager, MovementComponent movementComponent) {
		this.eventManager = eventManager;
		this.movementComponent = movementComponent;

		eventManager.register(EConstant.COLLISION_EVENT, this);
		eventManager.register(EConstant.MOMENTUM_TRANSFER_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.COLLISION_EVENT) {
			CollisionEvent collision = (CollisionEvent) event;
			double overlapX = collision.getOverlapX();
			double overlapY = collision.getOverlapY();
			double positionX = 0;
			double positionY = 0;
			if (collision.getHitbox1() == this.movementComponent.getHitbox()) {
				if (collision.isSolid()) {
					// Bounce off
					if (collision.isMoveable()) {
						// Energy transfer
						System.out.println("Wat");
						if (Math.abs(overlapX) < Math.abs(overlapY)) {
							// x-axis collision
							positionX = movementComponent.getPosition().getX() - overlapX;
							positionY = movementComponent.getPosition().getY();
							eventManager.raise(new MomentumTransferEvent(eventManager.getTime(), collision.getHitbox2(),
									movementComponent.getVelocityX(), 0));
							movementComponent.setVelocityX(0);
							movementComponent.setVelocityY(
									movementComponent.getVelocityY() * EConstant.FRICTION_COEFFICIENT);
						} else {
							// y-axis collision
							positionX = movementComponent.getPosition().getX();
							positionY = movementComponent.getPosition().getY() - overlapY;
							eventManager.raise(new MomentumTransferEvent(eventManager.getTime(), collision.getHitbox2(), 0,
									movementComponent.getVelocityY()));
							movementComponent.setVelocityY(0);
							movementComponent.setVelocityX(
									movementComponent.getVelocityX() * EConstant.FRICTION_COEFFICIENT);
						}
					} else {
						// Static surface
						if (Math.abs(overlapX) < Math.abs(overlapY)) {
							// x-axis collision
							positionX = movementComponent.getPosition().getX() - overlapX * 2;
							positionY = movementComponent.getPosition().getY();
							movementComponent.setVelocityX(
									movementComponent.getVelocityX() * -EConstant.COLLISION_ELASTICITY_COEFFICIENT);
							movementComponent.setVelocityY(
									movementComponent.getVelocityY() * EConstant.FRICTION_COEFFICIENT);
						} else {
							// y-axis collision
							positionX = movementComponent.getPosition().getX();
							positionY = movementComponent.getPosition().getY() - overlapY * 2;
							movementComponent.setVelocityY(
									movementComponent.getVelocityY() * -EConstant.COLLISION_ELASTICITY_COEFFICIENT);
							movementComponent.setVelocityX(
									movementComponent.getVelocityX() * EConstant.FRICTION_COEFFICIENT);
						}
					}
					eventManager.raise(new ObjectMovedEvent(eventManager.getTime(), movementComponent.getHitbox(), positionX, positionY));
				} else {
					// Pass through (do nothing)
				}
			} else if ((collision.getHitbox2() == this.movementComponent.getHitbox())) {
				if (collision.getHitbox1().isMoveable()) {
					// Energy transfer
					if (Math.abs(overlapX) < Math.abs(overlapY)) {
						// x-axis collision
						eventManager.raise(new MomentumTransferEvent(eventManager.getTime(), collision.getHitbox1(),
								movementComponent.getVelocityX(), 0));
						movementComponent.setVelocityX(0);
						movementComponent.setVelocityY(movementComponent.getVelocityY()
								*  EConstant.FRICTION_COEFFICIENT);
					} else {
						// y-axis collision
						eventManager.raise(new MomentumTransferEvent(eventManager.getTime(), collision.getHitbox1(), 0,
								-movementComponent.getVelocityY()));
						movementComponent.setVelocityY(0);
						movementComponent.setVelocityX(movementComponent.getVelocityX()
								* EConstant.FRICTION_COEFFICIENT);
					}
				}
			}
		} else if (event.getType() == EConstant.MOMENTUM_TRANSFER_EVENT
				&& ((MomentumTransferEvent) event).getHitbox() == movementComponent.getHitbox()) {
			movementComponent
					.setVelocityX(movementComponent.getVelocityX() + ((MomentumTransferEvent) event).getVelocityX());
			movementComponent
					.setVelocityY(movementComponent.getVelocityY() + ((MomentumTransferEvent) event).getVelocityY());
		}
	}
}

package component;

import event.CollisionEvent;
import event.MomentumTransferEvent;
import event.ObjectMovedEvent;
import event.Event;
import event.EventManager;
import util.EConstant;

public class CollisionPhysicsComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1550687788305067996L;
	MovementComponent movementComponent;

	public CollisionPhysicsComponent(int guid, EventManager eventManager, MovementComponent movementComponent) {
		super(guid, eventManager);
		this.eventManager = eventManager;
		this.movementComponent = movementComponent;
		register();
	}

	@Override
	public void register() {
		eventManager.register(EConstant.COLLISION_EVENT, this);
		eventManager.register(EConstant.MOMENTUM_TRANSFER_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.COLLISION_EVENT) {
			CollisionEvent cEvent = (CollisionEvent) event;
			double overlapX = cEvent.getOverlapX();
			double overlapY = cEvent.getOverlapY();
			double positionX = 0;
			double positionY = 0;
			if (cEvent.getOid1() == this.getGuid()) {
				if (cEvent.isSolid()) {
					// Bounce off
					if (cEvent.isMoveable()) {
						// Energy transfer
						if (Math.abs(overlapX) < Math.abs(overlapY)) {
							// x-axis collision
							positionX = movementComponent.getPosition().getX() - overlapX * 2;
							positionY = movementComponent.getPosition().getY();
							eventManager.raise(new MomentumTransferEvent(eventManager.getTime(), cEvent.getOid2(),
									movementComponent.getVelocityX(), 0));
							movementComponent.setVelocityX(0);
							movementComponent
									.setVelocityY(movementComponent.getVelocityY() * EConstant.FRICTION_COEFFICIENT);
						} else {
							// y-axis collision
							positionX = movementComponent.getPosition().getX();
							positionY = movementComponent.getPosition().getY() - overlapY * 2;
							eventManager.raise(new MomentumTransferEvent(eventManager.getTime(), cEvent.getOid2(), 0,
									movementComponent.getVelocityY()));
							movementComponent.setVelocityY(0);
							movementComponent
									.setVelocityX(movementComponent.getVelocityX() * EConstant.FRICTION_COEFFICIENT);
						}
					} else {
						// Static surface
						if (Math.abs(overlapX) < Math.abs(overlapY)) {
							// x-axis collision
							positionX = movementComponent.getPosition().getX() - overlapX * 2;
							positionY = movementComponent.getPosition().getY();
							movementComponent.setVelocityX(
									movementComponent.getVelocityX() * -EConstant.COLLISION_ELASTICITY_COEFFICIENT);
							movementComponent
									.setVelocityY(movementComponent.getVelocityY() * EConstant.FRICTION_COEFFICIENT);
						} else {
							// y-axis collision
							positionX = movementComponent.getPosition().getX();
							positionY = movementComponent.getPosition().getY() - overlapY * 2;
							movementComponent.setVelocityY(
									movementComponent.getVelocityY() * -EConstant.COLLISION_ELASTICITY_COEFFICIENT);
							movementComponent
									.setVelocityX(movementComponent.getVelocityX() * EConstant.FRICTION_COEFFICIENT);
						}
					}
					eventManager.raise(new ObjectMovedEvent(eventManager.getTime(), this.getGuid(), positionX, positionY,
							movementComponent.getHitbox().getWidth(), movementComponent.getHitbox().getHeight(), true));
				} else {
					// Pass through (do nothing)
				}
			} else if (cEvent.getOid2() == this.getGuid()) {
				if (cEvent.isMoveable()) {
					// Energy transfer
					if (Math.abs(overlapX) < Math.abs(overlapY)) {
						// x-axis collision
						eventManager.raise(new MomentumTransferEvent(eventManager.getTime(), cEvent.getOid1(),
								movementComponent.getVelocityX(), 0));
						movementComponent.setVelocityX(0);
						movementComponent
								.setVelocityY(movementComponent.getVelocityY() * EConstant.FRICTION_COEFFICIENT);
					} else {
						// y-axis collision
						eventManager.raise(new MomentumTransferEvent(eventManager.getTime(), cEvent.getOid1(), 0,
								-movementComponent.getVelocityY()));
						movementComponent.setVelocityY(0);
						movementComponent
								.setVelocityX(movementComponent.getVelocityX() * EConstant.FRICTION_COEFFICIENT);
					}
				}
			}
		} else if (event.getType() == EConstant.MOMENTUM_TRANSFER_EVENT) {
			MomentumTransferEvent mtEvent = (MomentumTransferEvent) event;
			if (mtEvent.getGuid() == this.getGuid()) {
				movementComponent.setVelocityX(movementComponent.getVelocityX() + mtEvent.getVelocityX());
				movementComponent.setVelocityY(movementComponent.getVelocityY() + mtEvent.getVelocityY());
			}
		}
	}
}

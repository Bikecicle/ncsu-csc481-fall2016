package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import util.EConstant;

public class CollisionBoxComponent implements Component, Driver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4425833608695605544L;
	private EventManager eventManager;
	private WorldPositionComponent position;
	private double width;
	private double height;
	private boolean solid;
	private boolean moveable;

	public CollisionBoxComponent(EventManager eventManager, WorldPositionComponent position, double width,
			double height, boolean solid, boolean moveable) {
		this.eventManager = eventManager;
		this.position = position;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.moveable = moveable;

		eventManager.register(EConstant.OBJECT_MOVED_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.OBJECT_MOVED_EVENT) {
			ObjectMovedEvent omEvent = (ObjectMovedEvent) event;

			if (omEvent.getHitbox() != this) {
				if (position.getX() - width / 2 < omEvent.getPositionX() + omEvent.getHitbox().getWidth() / 2
						&& omEvent.getPositionX() - omEvent.getHitbox().getWidth() / 2 < position.getX() + width / 2
						&& position.getY() - height / 2 < omEvent.getPositionY() + omEvent.getHitbox().getHeight() / 2
						&& omEvent.getPositionY() - omEvent.getHitbox().getHeight() / 2 < position.getY()
								+ height / 2) {
					double overlapX;
					double overlapY;
					if (omEvent.getPositionX() < position.getX()) {
						overlapX = (omEvent.getPositionX() + omEvent.getHitbox().getWidth() / 2)
								- (position.getX() - width / 2);
					} else {
						overlapX = (omEvent.getPositionX() - omEvent.getHitbox().getWidth() / 2)
								- (position.getX() + width / 2);
					}
					if (omEvent.getPositionY() < position.getY()) {
						overlapY = (omEvent.getPositionY() + omEvent.getHitbox().getHeight() / 2)
								- (position.getY() - height / 2);
					} else {
						overlapY = (omEvent.getPositionY() - omEvent.getHitbox().getHeight() / 2)
								- (position.getY() + height / 2);
					}
					eventManager.raise(
							new CollisionEvent(eventManager.getTime(), omEvent.getHitbox(), this, overlapX, overlapY));
				}
			}
		}
	}

	@Override
	public void drive(MovementComponent movementComponent) {
		// TODO Auto-generated method stub

	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public WorldPositionComponent getPosition() {
		return position;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isMoveable() {
		return moveable;
	}

}

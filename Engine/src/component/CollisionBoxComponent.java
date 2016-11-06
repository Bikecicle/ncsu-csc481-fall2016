package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import util.EConstant;

public class CollisionBoxComponent implements Component, Driver {

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
	public void update() {
		// Do nothing
	}

	@Override
	public void onEvent(Event event) {
		MovementComponent mc = ((ObjectMovedEvent) event).getMovementComponent();
		if (mc != null && mc.getHitbox() != this) {
			if (position.getX() - width / 2 < mc.getPosition().getX() + mc.getHitbox().getWidth() / 2
					&& mc.getPosition().getX() - mc.getHitbox().getWidth() / 2 < position.getX() + width / 2
					&& position.getY() - height / 2 < mc.getPosition().getY() + mc.getHitbox().getHeight() / 2
					&& mc.getPosition().getY() - mc.getHitbox().getHeight() / 2 < position.getY() + this.height / 2) {
				double overlapX;
				double overlapY;
				if (mc.getVelocityX() >= 0) {
					overlapX = (mc.getPosition().getX() + mc.getHitbox().getWidth() / 2)
							- (position.getX() - width / 2);
				} else {
					overlapX = (mc.getPosition().getX() - mc.getHitbox().getWidth() / 2)
							- (position.getX() + width / 2);
				}
				if (mc.getVelocityY() >= 0) {
					overlapY = (mc.getPosition().getY() + mc.getHitbox().getHeight() / 2)
							- (position.getY() - height / 2);
				} else {
					overlapY = (mc.getPosition().getY() - mc.getHitbox().getHeight() / 2)
							- (position.getY() + height / 2);
				}
				eventManager.raise(new CollisionEvent(mc.getHitbox(), this, overlapX, overlapY));
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

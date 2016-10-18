package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import util.EConstant;

public class CollisionBoxComponent implements Component, Driver {

	private EventManager eventManager;
	private WorldPositionComponent position;
	private int width;
	private int height;
	private boolean moveable;

	public CollisionBoxComponent(EventManager eventManager, WorldPositionComponent position, int width, int height,
			boolean moveable) {
		this.eventManager = eventManager;
		this.position = position;
		this.width = width;
		this.height = height;
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
		if (mc != null && (position.getX() < mc.getPosition().getX() + mc.getHitbox().getWidth()
				&& mc.getPosition().getX() < position.getX() + this.width
				&& position.getY() < mc.getPosition().getY() + mc.getHitbox().getHeight()
				&& mc.getPosition().getY() < position.getY() + this.height) == true) {
			eventManager.raise(new CollisionEvent(mc, this));
		}
	}

	@Override
	public void drive(MovementComponent movementComponent) {
		// TODO Auto-generated method stub

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

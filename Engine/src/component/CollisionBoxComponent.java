package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import util.EConstant;

public class CollisionBoxComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4425833608695605544L;
	private WorldPositionComponent position;
	private double width;
	private double height;
	private boolean solid;
	private boolean moveable;

	public CollisionBoxComponent(int guid, EventManager eventManager, WorldPositionComponent position, double width,
			double height, boolean solid, boolean moveable) {
		super(guid, eventManager);
		this.eventManager = eventManager;
		this.position = position;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.moveable = moveable;
		register();
	}
	

	@Override
	public void register() {
		eventManager.register(EConstant.OBJECT_MOVED_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.OBJECT_MOVED_EVENT) {
			ObjectMovedEvent omEvent = (ObjectMovedEvent) event;

			if (omEvent.getGuid() != this.getGuid() && !omEvent.isChain()) {
				if (position.getX() - width / 2 < omEvent.getPositionX() + omEvent.getWidth() / 2
						&& omEvent.getPositionX() - omEvent.getWidth() / 2 < position.getX() + width / 2
						&& position.getY() - height / 2 < omEvent.getPositionY() + omEvent.getHeight() / 2
						&& omEvent.getPositionY() - omEvent.getHeight() / 2 < position.getY()
								+ height / 2) {
					double overlapX;
					double overlapY;
					if (omEvent.getPositionX() < position.getX()) {
						overlapX = (omEvent.getPositionX() + omEvent.getWidth() / 2)
								- (position.getX() - width / 2);
					} else {
						overlapX = (omEvent.getPositionX() - omEvent.getWidth() / 2)
								- (position.getX() + width / 2);
					}
					if (omEvent.getPositionY() < position.getY()) {
						overlapY = (omEvent.getPositionY() + omEvent.getHeight() / 2)
								- (position.getY() - height / 2);
					} else {
						overlapY = (omEvent.getPositionY() - omEvent.getHeight() / 2)
								- (position.getY() + height / 2);
					}
					eventManager.raise(
							new CollisionEvent(eventManager.getTime(), omEvent.getGuid(), this.getGuid(), overlapX, overlapY, solid, moveable));
				}
			}
		}
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


	@Override
	public Component copy() {
		return new CollisionBoxComponent(guid, eventManager, position, width, height, solid, moveable);
	}
}

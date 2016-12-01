package component;

import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import util.EConstant;

public class WorldPositionComponent extends Component {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7248611186555109028L;
	private double positionX, positionY;

	public WorldPositionComponent(int guid, EventManager eventManager, double positionX, double positionY) {
		super(guid, eventManager);
		this.eventManager = eventManager;
		this.positionX = positionX;
		this.positionY = positionY;
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
			if (omEvent.getGuid() == this.getGuid()) {
				positionX = omEvent.getPositionX();
				positionY = omEvent.getPositionY();
			}
		}
	}
	
	public double getX() {
		return positionX;
	}

	public double getY() {
		return positionY;
	}
	
	public void moveX(double r) {
		positionX += r;
	}
	
	public void moveY(double r) {
		positionY += r;
	}
}

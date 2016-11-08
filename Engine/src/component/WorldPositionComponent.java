package component;

import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import util.EConstant;

public class WorldPositionComponent implements Component {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7248611186555109028L;
	private double positionX, positionY;

	public WorldPositionComponent(EventManager eventManager, double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		
		eventManager.register(EConstant.OBJECT_MOVED_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.OBJECT_MOVED_EVENT) {
			ObjectMovedEvent omEvent = (ObjectMovedEvent) event;
			if (omEvent.getHitbox().getPosition() == this) {
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
	
	public String toString() {
		return "x: " + positionX + " y: " + positionY;
	}
}

package component;

import event.Event;
import event.EventManager;

public class WorldPositionComponent implements Component {
	private double positionX;
	private double positionY;

	public WorldPositionComponent(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public WorldPositionComponent() {
		this.positionX = 50;
		this.positionY = 50;
	}

	@Override
	public void update() {
		// Do nothing
	}

	@Override
	public void onEvent(Event event) {
		// Do nothing
	}
	
	public double getX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	

}

package component;

import event.Event;

public class WorldPositionComponent implements Component {
	private double positionX;
	private double positionY;

	public WorldPositionComponent(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public WorldPositionComponent() {
		this.positionX = 50;
		this.positionY = 50;
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
	
	public void moveX( double dx){
		this.positionX += dx;
	}
	
	public void moveY( double dy){
		this.positionY += dy;
	}
	
	public String toString() {
		return "x: " + positionX + " y: " + positionY;
	}
}

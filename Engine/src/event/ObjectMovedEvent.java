package event;

import util.EConstant;

public class ObjectMovedEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5063568448250232841L;
	private int oid;
	private double positionX, positionY, width, height;

	public ObjectMovedEvent(long timestamp, int oid, double positionX, double positionY, double width, double height) {
		super(timestamp, EConstant.OBJECT_MOVED_EVENT, EConstant.PRIORITY_MOVEMENT);
		this.oid = oid;
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
	}
	
	public int getOid() {
		return oid;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
}

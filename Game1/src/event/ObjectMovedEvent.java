package event;

import util.EConstant;

public class ObjectMovedEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5063568448250232841L;
	private int guid;
	private double positionX, positionY, width, height;
	private boolean chain;

	public ObjectMovedEvent(long timestamp, int guid, double positionX, double positionY, double width, double height, boolean chain) {
		super(timestamp, EConstant.OBJECT_MOVED_EVENT, EConstant.PRIORITY_MOVEMENT);
		this.guid = guid;
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
		this.chain = chain;
	}
	
	public int getGuid() {
		return guid;
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
	
	public boolean isChain() {
		return chain;
	}
}

package event;

import util.EConstant;

public class MomentumTransferEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8368896505955127730L;
	private int guid;
	private double velocityX, velocityY;

	public MomentumTransferEvent(long timestamp, int guid, double velocityX, double velocityY) {
		super(timestamp, EConstant.MOMENTUM_TRANSFER_EVENT, EConstant.PRIORITY_PHYSICS);
		this.guid = guid;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}

	public int getGuid() {
		return guid;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}
}

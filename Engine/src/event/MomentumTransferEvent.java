package event;

import util.EConstant;

public class MomentumTransferEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8368896505955127730L;
	private int oid;
	private double velocityX, velocityY;

	public MomentumTransferEvent(long timestamp, int oid, double velocityX, double velocityY) {
		super(timestamp, EConstant.MOMENTUM_TRANSFER_EVENT, EConstant.PRIORITY_PHYSICS);
		this.oid = oid;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}

	public int getOid() {
		return oid;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}
}

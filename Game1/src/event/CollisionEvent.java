package event;

import util.EConstant;

public class CollisionEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2609642516976356988L;
	private int oid1, oid2;
	private double overlapX, overlapY;
	private boolean solid;
	private boolean moveable;

	public CollisionEvent(long timestamp, int oid1, int oid2, double overlapX,
			double overlapY, boolean solid, boolean moveable) {
		super(timestamp, EConstant.COLLISION_EVENT, EConstant.PRIORITY_PHYSICS);
		this.oid1 = oid1;
		this.oid2 = oid2;
		this.overlapX = overlapX;
		this.overlapY = overlapY;
		this.solid = solid;
		this.moveable = moveable;
	}
	
	public int getOid1() {
		return oid1;
	}
	
	public int getOid2() {
		return oid2;
	}

	public double getOverlapX() {
		return overlapX;
	}

	public double getOverlapY() {
		return overlapY;
	}

	public boolean isCollidingFromBelow() {
		return overlapY > 0 && Math.abs(overlapY) < Math.abs(overlapX);
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isMoveable() {
		return moveable;
	}
}

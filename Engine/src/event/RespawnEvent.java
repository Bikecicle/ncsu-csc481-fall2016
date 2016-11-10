package event;

import util.EConstant;

public class RespawnEvent extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6936436040086450288L;
	private int oid;
	private double width, height;
	private boolean spawned;

	public RespawnEvent(Long timestamp, int oid, double width, double height) {
		super(timestamp, EConstant.RESPAWN_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.oid = oid;
		this.spawned = false;
	}
	
	public int getOid() {
		return oid;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public boolean isSpawned() {
		return spawned;
	}
	
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
}

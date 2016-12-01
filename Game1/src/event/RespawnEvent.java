package event;

import util.EConstant;

public class RespawnEvent extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6936436040086450288L;
	private int guid;
	private double width, height;
	private boolean spawned;

	public RespawnEvent(Long timestamp, int guid, double width, double height) {
		super(timestamp, EConstant.RESPAWN_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.guid = guid;
		this.spawned = false;
		System.out.println("respawn event");
	}
	
	public int getGuid() {
		return guid;
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

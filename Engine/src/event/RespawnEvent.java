package event;

import component.WorldPositionComponent;
import util.EConstant;

public class RespawnEvent extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6936436040086450288L;
	private WorldPositionComponent position;
	private boolean spawned;

	public RespawnEvent(Long timestamp, WorldPositionComponent position) {
		super(timestamp, EConstant.RESPAWN_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.position = position;
		this.spawned = false;
	}
	
	public WorldPositionComponent getPosition() {
		return position;
	}
	
	public boolean isSpawned() {
		return spawned;
	}
	
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
}

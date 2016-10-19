package event;

import component.WorldPositionComponent;
import util.EConstant;

public class RespawnEvent extends Event {
	
	private WorldPositionComponent position;
	private boolean spawned;

	public RespawnEvent(WorldPositionComponent position) {
		super(EConstant.RESPAWN_EVENT, EConstant.PRIORITY_MEDIUM);
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

package event;

import component.CollisionBoxComponent;
import component.WorldPositionComponent;
import util.EConstant;

public class RespawnEvent extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6936436040086450288L;
	private CollisionBoxComponent hitbox;
	private boolean spawned;

	public RespawnEvent(Long timestamp, CollisionBoxComponent hitbox) {
		super(timestamp, EConstant.RESPAWN_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.hitbox = hitbox;
		this.spawned = false;
	}
	
	public CollisionBoxComponent getHitbox() {
		return hitbox;
	}
	
	public boolean isSpawned() {
		return spawned;
	}
	
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
}

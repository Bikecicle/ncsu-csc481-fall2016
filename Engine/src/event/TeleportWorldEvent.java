package event;

import gameobject.World;
import util.EConstant;

public class TeleportWorldEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5900366372536261883L;
	World world;
	
	public TeleportWorldEvent(long timestamp, World world) {
		super(timestamp, EConstant.TELEPORT_WORLD_EVENT, EConstant.PRIORITY_NETWORKING);
		this.world = world;
	}
	
	public World getWorld() {
		return world;
	}
}

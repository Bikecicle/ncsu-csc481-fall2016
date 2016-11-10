package event;

import util.EConstant;

public class WorldRequestEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867478695253959139L;
	private int clientId;

	public WorldRequestEvent(long timestamp, int clientId) {
		super(timestamp, EConstant.WORLD_REQUEST_EVENT, EConstant.PRIORITY_NETWORKING);
		this.clientId = clientId;
	}
	
	public int getClientId() {
		return clientId;
	}
}

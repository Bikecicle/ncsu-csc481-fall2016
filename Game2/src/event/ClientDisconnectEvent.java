package event;

import util.EConstant;

public class ClientDisconnectEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1624713493777915075L;
	private int id;

	public ClientDisconnectEvent(long timestamp, int id) {
		super(timestamp, EConstant.CLIENT_DISCONNECT_EVENT, EConstant.PRIORITY_NETWORKING);
		this.id = id;
	}

	public int getId() {
		return id;
	}
}

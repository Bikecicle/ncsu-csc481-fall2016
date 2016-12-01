package event;

import util.EConstant;

public class ConnectionLostEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3206210773090052638L;

	public ConnectionLostEvent(long timestamp) {
		super(timestamp, EConstant.CONNECTION_LOST_EVENT, EConstant.PRIORITY_NETWORKING);
	}

}

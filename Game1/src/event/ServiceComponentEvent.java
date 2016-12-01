package event;

import util.EConstant;

public class ServiceComponentEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7640075409741189083L;
	private int guid;

	public ServiceComponentEvent(long timestamp, int guid) {
		super(timestamp, EConstant.SERVICE_COMPONENT_EVENT, EConstant.PRIORITY_MOVEMENT);
		this.guid = guid;
	}

	public int getGuid() {
		return guid;
	}
}

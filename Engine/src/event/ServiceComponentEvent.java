package event;

import util.EConstant;

public class ServiceComponentEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7640075409741189083L;
	private int oid;

	public ServiceComponentEvent(long timestamp, int oid) {
		super(timestamp, EConstant.SERVICE_COMPONENT_EVENT, EConstant.PRIORITY_MOVEMENT);
		this.oid = oid;
	}

	public int getOid() {
		return oid;
	}
}

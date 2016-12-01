package event;

import util.EConstant;

public class ServiceAllEvent extends Event{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4003603221757322438L;

	public ServiceAllEvent(long timestamp) {
		super(timestamp, EConstant.SERVICE_ALL_EVENT, EConstant.PRIORITY_MOVEMENT);
	}

}

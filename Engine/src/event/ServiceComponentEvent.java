package event;

import component.Component;
import util.EConstant;

public class ServiceComponentEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7640075409741189083L;
	Component component;

	public ServiceComponentEvent(long timestamp, Component component) {
		super(timestamp, EConstant.SERVICE_COMPONENT_EVENT, EConstant.PRIORITY_MOVEMENT);
		this.component = component;
	}

	public Component getComponent() {
		return component;
	}
}

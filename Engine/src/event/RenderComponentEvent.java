package event;

import component.Renderable;
import util.EConstant;

public class RenderComponentEvent extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7598256964529546950L;
	Renderable component;

	public RenderComponentEvent(Long timestamp, Renderable component) {
		super(timestamp, EConstant.RENDER_COMPONENT_EVENT, EConstant.PRIORITY_RENDERING);
		this.component = component;
	}
	
	public Renderable getComponent() {
		return component;
	}

}

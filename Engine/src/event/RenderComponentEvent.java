package event;

import rendering.Shape;
import util.EConstant;

public class RenderComponentEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7598256964529546950L;
	private Shape shape;
	
	public RenderComponentEvent(Long timestamp, Shape shape) {
		super(timestamp, EConstant.RENDER_COMPONENT_EVENT, EConstant.PRIORITY_RENDERING);
		this.shape = shape;
	}

	public Shape getShape() {
		return shape;
	}
}

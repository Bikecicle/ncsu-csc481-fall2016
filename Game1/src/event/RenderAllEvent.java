package event;

import util.EConstant;

public class RenderAllEvent extends Event{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6397389352361688140L;

	public RenderAllEvent(long timestamp) {
		super(timestamp, EConstant.RENDER_ALL_EVENT, EConstant.PRIORITY_RENDERING);
	}

}

package event;

import util.EConstant;

public class RenderEvent extends Event {

	public RenderEvent() {
		super(EConstant.RENDER_EVENT, EConstant.PRIORITY_HIGHEST);
	}

}

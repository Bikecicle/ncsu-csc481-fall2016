package event;

import util.EConstant;

public class RenderComponentEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7598256964529546950L;
	private int oid;

	public RenderComponentEvent(Long timestamp, int oid) {
		super(timestamp, EConstant.RENDER_COMPONENT_EVENT, EConstant.PRIORITY_RENDERING);
		this.oid = oid;
	}

	public int getOid() {
		return oid;
	}
}

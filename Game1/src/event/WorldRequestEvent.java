package event;

import util.EConstant;

public class WorldRequestEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867478695253959139L;
	private int source;
	private int target;

	public WorldRequestEvent(long timestamp, int source, int target) {
		super(timestamp, EConstant.WORLD_REQUEST_EVENT, EConstant.PRIORITY_NETWORKING);
		this.source = source;
		this.target = target;
	}
	
	public int getSource() {
		return source;
	}
	
	public int getTarget() {
		return target;
	}
}

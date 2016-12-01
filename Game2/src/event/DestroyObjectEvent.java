package event;

import util.EConstant;

public class DestroyObjectEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4959431294056338893L;
	private int guid;
	
	public DestroyObjectEvent(long timestamp, int guid) {
		super(timestamp, EConstant.DESTROY_OBJECT_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.guid = guid;
	}

	public int getGuid() {
		return guid;
	}
}

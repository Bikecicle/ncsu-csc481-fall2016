package event;

import util.EConstant;

public class ObjectDamageEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7117258340909374019L;
	private int guid;

	public ObjectDamageEvent(Long timestamp, int guid) {
		super(timestamp, EConstant.OBJECT_DAMAGE_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.guid = guid;
	}

	public int getGuid() {
		return guid;
	}
}

package event;

import util.EConstant;

public class ObjectDamageEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7117258340909374019L;
	private int oid;

	public ObjectDamageEvent(Long timestamp, int oid) {
		super(timestamp, EConstant.OBJECT_DAMAGE_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.oid = oid;
	}

	public int getOid() {
		return oid;
	}
}

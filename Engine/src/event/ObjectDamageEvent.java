package event;

import component.WorldPositionComponent;
import util.EConstant;

public class ObjectDamageEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7117258340909374019L;
	private WorldPositionComponent position;

	public ObjectDamageEvent(Long timestamp, WorldPositionComponent position) {
		super(timestamp, EConstant.OBJECT_DAMAGE_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.position = position;
	}

	public WorldPositionComponent getPosition() {
		return position;
	}
}

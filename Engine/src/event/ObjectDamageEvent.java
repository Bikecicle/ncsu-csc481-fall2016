package event;

import component.WorldPositionComponent;
import util.EConstant;

public class ObjectDamageEvent extends Event {

	private WorldPositionComponent position;

	public ObjectDamageEvent(WorldPositionComponent position) {
		super(EConstant.OBJECT_DAMAGE_EVENT, EConstant.PRIORITY_MEDIUM);
		this.position = position;
	}

	public WorldPositionComponent getPosition() {
		return position;
	}
}

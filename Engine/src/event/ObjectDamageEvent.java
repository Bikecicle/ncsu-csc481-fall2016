package event;

import component.CollisionBoxComponent;
import component.WorldPositionComponent;
import util.EConstant;

public class ObjectDamageEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7117258340909374019L;
	private CollisionBoxComponent hitbox;

	public ObjectDamageEvent(Long timestamp, CollisionBoxComponent hitbox) {
		super(timestamp, EConstant.OBJECT_DAMAGE_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.hitbox = hitbox;
	}

	public CollisionBoxComponent getHitbox() {
		return hitbox;
	}
}

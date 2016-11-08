package component;

import event.Event;
import event.EventManager;
import event.ObjectDamageEvent;
import event.RespawnEvent;
import util.EConstant;

public class RespawnComponent implements Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6226201588878691649L;
	private EventManager eventManager;
	private CollisionBoxComponent hitbox;

	public RespawnComponent(EventManager eventManager, CollisionBoxComponent hitbox) {
		this.eventManager = eventManager;
		this.hitbox = hitbox;

		eventManager.register(EConstant.OBJECT_DAMAGE_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.OBJECT_DAMAGE_EVENT) {
			ObjectDamageEvent odEvent = (ObjectDamageEvent) event;
			if (odEvent.getHitbox() == hitbox) {
				eventManager.raise(new RespawnEvent(eventManager.getTime(), hitbox));
			}
		}
	}

}

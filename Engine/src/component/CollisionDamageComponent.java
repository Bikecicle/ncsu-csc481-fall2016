package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import event.ObjectDamageEvent;
import util.EConstant;

public class CollisionDamageComponent implements Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7242958124205217848L;
	EventManager eventManager;
	CollisionBoxComponent hitbox;

	public CollisionDamageComponent(EventManager eventManager, CollisionBoxComponent hitbox) {
		this.eventManager = eventManager;
		this.hitbox = hitbox;

		eventManager.register(EConstant.COLLISION_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.COLLISION_EVENT) {
			CollisionEvent cEvent = (CollisionEvent) event;
			if (cEvent.getHitbox2() == hitbox) {
				eventManager.raise(new ObjectDamageEvent(eventManager.getTime(),
						cEvent.getHitbox1()));
			}
		}
	}
}

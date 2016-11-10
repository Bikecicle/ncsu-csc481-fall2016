package component;

import event.Event;
import event.EventManager;
import event.ObjectDamageEvent;
import event.RespawnEvent;
import util.EConstant;

public class RespawnComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6226201588878691649L;
	private CollisionBoxComponent hitbox;

	public RespawnComponent(int oid, EventManager eventManager, CollisionBoxComponent hitbox) {
		super(oid, eventManager);
		this.eventManager = eventManager;
		this.hitbox = hitbox;
		register();
	}
	
	@Override
	public void register() {
		eventManager.register(EConstant.OBJECT_DAMAGE_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.OBJECT_DAMAGE_EVENT) {
			ObjectDamageEvent odEvent = (ObjectDamageEvent) event;
			if (odEvent.getOid() == this.getOid()) {
				eventManager.raise(new RespawnEvent(eventManager.getTime(), this.getOid(), hitbox.getWidth(), hitbox.getHeight()));
			}
		}
	}

	@Override
	public Component copy() {
		return new RespawnComponent(oid, eventManager, hitbox);
	}
}

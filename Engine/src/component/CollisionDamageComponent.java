package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import event.ObjectDamageEvent;
import util.EConstant;

public class CollisionDamageComponent implements Component{
	
	EventManager eventManager;
	CollisionBoxComponent hitbox;
	
	public CollisionDamageComponent( EventManager eventManager, CollisionBoxComponent hitbox ) {
		this.eventManager = eventManager;
		this.hitbox = hitbox;
		
		eventManager.register(EConstant.COLLISION_EVENT, this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.COLLISION_EVENT && ((CollisionEvent) event).getHitbox2() == hitbox) {
			eventManager.raise(new ObjectDamageEvent(((CollisionEvent) event).getHitbox1().getPosition()));
		}
	}
}

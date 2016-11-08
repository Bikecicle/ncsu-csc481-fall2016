package component;

import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import event.RespawnEvent;
import util.EConstant;

public class SpawnPointComponent implements Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8338102255679833331L;
	private EventManager eventManager;
	private WorldPositionComponent position;

	public SpawnPointComponent(EventManager eventManager, WorldPositionComponent position) {
		this.eventManager = eventManager;
		this.position = position;

		eventManager.register(EConstant.RESPAWN_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.RESPAWN_EVENT) {
			RespawnEvent rEvent = (RespawnEvent) event;
			if (!rEvent.isSpawned()) {
				eventManager.raise(new ObjectMovedEvent(eventManager.getTime(), rEvent.getHitbox(), position.getX(), position.getY()));
				rEvent.setSpawned(true);
			}
		}
	}

}

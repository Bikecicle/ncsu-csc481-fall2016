package component;

import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import event.RespawnEvent;
import util.EConstant;

public class SpawnPointComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8338102255679833331L;
	private WorldPositionComponent position;

	public SpawnPointComponent(int id, EventManager eventManager, WorldPositionComponent position) {
		super(id, eventManager);
		this.eventManager = eventManager;
		this.position = position;
		register();
	}

	@Override
	public void register() {
		eventManager.register(EConstant.RESPAWN_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.RESPAWN_EVENT) {
			RespawnEvent rEvent = (RespawnEvent) event;
			if (!rEvent.isSpawned()) {
				eventManager.raise(new ObjectMovedEvent(eventManager.getTime(), rEvent.getOid(), position.getX(),
						position.getY(), rEvent.getWidth(), rEvent.getHeight()));
				rEvent.setSpawned(true);
			}
		}
	}
}

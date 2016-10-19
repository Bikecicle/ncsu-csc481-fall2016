package component;

import event.Event;
import event.EventManager;
import event.RespawnEvent;
import util.EConstant;

public class SpawnPointComponent implements Component {

	private EventManager eventManager;
	private WorldPositionComponent position;

	public SpawnPointComponent(EventManager eventManager, WorldPositionComponent position) {
		this.eventManager = eventManager;
		this.position = position;

		eventManager.register(EConstant.RESPAWN_EVENT, this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEvent(Event event) {
		if (!((RespawnEvent) event).isSpawned()) {
			((RespawnEvent) event).getPosition().setPositionX(position.getX());
			((RespawnEvent) event).getPosition().setPositionY(position.getY());
			((RespawnEvent) event).setSpawned(true);
		}
	}

}

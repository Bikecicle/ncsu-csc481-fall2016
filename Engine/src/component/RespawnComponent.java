package component;

import event.Event;
import event.EventManager;
import event.ObjectDamageEvent;
import event.RespawnEvent;
import util.EConstant;

public class RespawnComponent implements Component {

	private EventManager eventManager;
	private WorldPositionComponent position;

	public RespawnComponent(EventManager eventManager, WorldPositionComponent position) {
		this.eventManager = eventManager;
		this.position = position;

		eventManager.register(EConstant.OBJECT_DAMAGE_EVENT, this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEvent(Event event) {
		if (((ObjectDamageEvent) event).getPosition() == position) {
			eventManager.raise(new RespawnEvent(eventManager.getTime(), position));
		}
	}

}

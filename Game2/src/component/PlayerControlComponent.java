package component;

import event.CreateObjectEvent;
import event.DestroyObjectEvent;
import event.Event;
import event.EventManager;
import event.KeyPressedEvent;
import event.KeyReleasedEvent;
import gameobject.Projectile;
import util.EConstant;
import util.PressedKeyMap;

public class PlayerControlComponent extends Component implements MovementDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9065247151360359992L;
	private PressedKeyMap map;
	private int playerId;
	private boolean firing;

	public PlayerControlComponent(int guid, EventManager eventManager, int playerId) {
		super(guid, eventManager);
		this.map = new PressedKeyMap();
		this.playerId = playerId;
		register();

		map.release(EConstant.KEY_UP);
		map.release(EConstant.KEY_DOWN);
		map.release(EConstant.KEY_LEFT);
		map.release(EConstant.KEY_RIGHT);
	}

	@Override
	public void register() {
		eventManager.register(EConstant.KEY_PRESSED_EVENT, this);
		eventManager.register(EConstant.KEY_RELEASED_EVENT, this);
		eventManager.register(EConstant.DESTROY_OBJECT_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.KEY_PRESSED_EVENT) {
			KeyPressedEvent kpEvent = (KeyPressedEvent) event;
			if (kpEvent.getId() == playerId) {
				map.press(kpEvent.getKey());
			}
		} else if (event.getType() == EConstant.KEY_RELEASED_EVENT) {
			KeyReleasedEvent krEvent = (KeyReleasedEvent) event;
			if (krEvent.getId() == playerId) {
				map.release(krEvent.getKey());
			}
		} else if (event.getType() == EConstant.DESTROY_OBJECT_EVENT) {
			DestroyObjectEvent doEvent = (DestroyObjectEvent) event;
			if (doEvent.getGuid() == "projectile".hashCode()) {
				firing = false;
			}
		}
	}

	@Override
	public void drive(MovementComponent motion) {
		if (map.pressed(EConstant.KEY_UP)) {
			if (!firing) {
				eventManager.raise(new CreateObjectEvent(eventManager.getTime(),
						new Projectile("projectile", motion.getPosition().getX(), 14, 1, 1)));
				firing = true;
			}
		} else if (map.pressed(EConstant.KEY_LEFT)) {
			motion.setTempPositionX(motion.getPosition().getX() - 50 * motion.getDt());
		} else if (map.pressed(EConstant.KEY_RIGHT)) {
			motion.setTempPositionX(motion.getPosition().getX() + 50 * motion.getDt());
		}
	}
}

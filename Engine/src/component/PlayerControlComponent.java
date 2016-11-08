package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import event.KeyPressedEvent;
import event.KeyReleasedEvent;
import util.EConstant;
import util.PressedKeyMap;

public class PlayerControlComponent implements Component, Driver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9065247151360359992L;
	private PressedKeyMap map;
	private CollisionBoxComponent collisionBoxComponent;
	private boolean jumping;
	private int playerId;

	public PlayerControlComponent(EventManager eventManager, CollisionBoxComponent collisionBoxComponent,
			int playerId) {
		this.map = new PressedKeyMap();
		this.playerId = playerId;
		this.collisionBoxComponent = collisionBoxComponent;

		eventManager.register(EConstant.KEY_PRESSED_EVENT, this);
		eventManager.register(EConstant.KEY_RELEASED_EVENT, this);
		eventManager.register(EConstant.COLLISION_EVENT, this);

		map.release(EConstant.KEY_UP);
		map.release(EConstant.KEY_DOWN);
		map.release(EConstant.KEY_LEFT);
		map.release(EConstant.KEY_RIGHT);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.KEY_PRESSED_EVENT && ((KeyPressedEvent) event).getId() == playerId) {
			map.press(((KeyPressedEvent) event).getKey());
		} else if (event.getType() == EConstant.KEY_RELEASED_EVENT && ((KeyReleasedEvent) event).getId() == playerId) {
			map.release(((KeyReleasedEvent) event).getKey());
		} else if (event.getType() == EConstant.COLLISION_EVENT
				&& ((CollisionEvent) event).getHitbox1() == this.collisionBoxComponent) {
			if (!((CollisionEvent) event).isCollidingFromBelow()) {
				jumping = false;
			}
		}
	}

	@Override
	public void drive(MovementComponent motion) {
		if (map.pressed(EConstant.KEY_UP)) {
			if (!jumping) {
				motion.setVelocityY(motion.getVelocityY() + EConstant.PLAYER_JUMP_VELOCITY);
				jumping = true;
			}
		} else if (map.pressed(EConstant.KEY_LEFT)) {
			if (motion.getVelocityX() > -EConstant.PLAYER_MAX_VELOCITY) {
				motion.setVelocityX(motion.getVelocityX() - EConstant.PLAYER_ACCELERATION * motion.getDt());
			}
		} else if (map.pressed(EConstant.KEY_RIGHT)) {
			if (motion.getVelocityX() < EConstant.PLAYER_MAX_VELOCITY) {
				motion.setVelocityX(motion.getVelocityX() + EConstant.PLAYER_ACCELERATION * motion.getDt());
			}
		}
	}

}

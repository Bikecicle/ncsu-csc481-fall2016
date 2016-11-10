package component;

import event.Event;
import event.EventManager;
import util.EConstant;

public class GravityComponent extends Component implements Driver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2370007356638300795L;

	public GravityComponent(int oid, EventManager eventManager) {
		super(oid, eventManager);
		register();
	}
	

	@Override
	public void register() {
		// Do nothing
	}

	@Override
	public void onEvent(Event event) {
		// Do nothing
	}

	@Override
	public void drive(MovementComponent movementComponent) {
		movementComponent.setVelocityY(movementComponent.getVelocityY() + EConstant.GRAVITY * movementComponent.getDt());
	}


	@Override
	public Component copy() {
		return new GravityComponent(oid, eventManager);
	}
}

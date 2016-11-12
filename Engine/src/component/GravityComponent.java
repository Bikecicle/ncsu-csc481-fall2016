package component;

import event.Event;
import event.EventManager;
import util.EConstant;

public class GravityComponent extends Component implements Driver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2370007356638300795L;

	public GravityComponent(int guid, EventManager eventManager) {
		super(guid, eventManager);
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
}

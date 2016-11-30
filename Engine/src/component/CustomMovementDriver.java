package component;

import event.Event;
import event.EventManager;

public class CustomMovementDriver extends Component implements MovementDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6301334661429358082L;

	public CustomMovementDriver(int guid, EventManager eventManager, String driverScript) {
		super(guid, eventManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drive(MovementComponent movementComponent) {
		
	}

}

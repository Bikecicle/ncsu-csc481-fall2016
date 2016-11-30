package component;

import event.Event;
import event.EventManager;
import scripting.ScriptManager;

public class CustomMovementDriver extends Component implements MovementDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6301334661429358082L;
	private String driverScript;

	public CustomMovementDriver(int guid, EventManager eventManager, String driverScript) {
		super(guid, eventManager);
		this.driverScript = driverScript;
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
		ScriptManager.bindArgument("movement_component", movementComponent);
		ScriptManager.loadScript("scripts/" + driverScript + ".js");
	}

}

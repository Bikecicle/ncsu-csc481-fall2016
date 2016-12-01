package component;

import event.Event;
import event.EventManager;
import scripting.ScriptManager;
import util.EConstant;

public class CustomDriverComponent extends Component implements MovementDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6301334661429358082L;
	private String driverScript;
	private double x0, y0;

	public CustomDriverComponent(int guid, EventManager eventManager, String driverScript, double x0, double y0) {
		super(guid, eventManager);
		this.driverScript = driverScript;
		this.setX0(x0);
		this.setY0(y0);
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
		ScriptManager.bindArgument("initial_x", x0);
		ScriptManager.bindArgument("initial_y", y0);
		ScriptManager.bindArgument("time", eventManager.getTime());
		ScriptManager.loadScript(EConstant.SCRIPT_HOME + driverScript);
		ScriptManager.executeScript();
	}

	public double getX0() {
		return x0;
	}

	public void setX0(double x0) {
		this.x0 = x0;
	}

	public double getY0() {
		return y0;
	}

	public void setY0(double y0) {
		this.y0 = y0;
	}

}

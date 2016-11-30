package component;

import event.Event;
import event.EventManager;
import scripting.ScriptManager;
import util.EConstant;

public class CustomDamageComponent extends Component {

	public CustomDamageComponent(int guid, EventManager eventManager) {
		super(guid, eventManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register() {
		eventManager.register(EConstant.COLLISION_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		ScriptManager.bindArgument("collision", event);
		ScriptManager.bindArgument(name, obj);
		ScriptManager.loadScript("scripts/" + driverScript + ".js");
	}

}

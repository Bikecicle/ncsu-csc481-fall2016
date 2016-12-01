package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import scripting.ScriptManager;
import util.EConstant;

public class CustomDamageComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1441428099900337773L;
	private String damageScript;
	
	public CustomDamageComponent(int guid, EventManager eventManager, String damageScript) {
		super(guid, eventManager);
		this.damageScript = damageScript;
		register();
	}

	@Override
	public void register() {
		eventManager.register(EConstant.COLLISION_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.COLLISION_EVENT) {
			CollisionEvent cEvent = (CollisionEvent) event;
			if (cEvent.getOid2() == guid) {
				System.out.println("death zone hit");
				ScriptManager.bindArgument("collision", (CollisionEvent) event);
				ScriptManager.bindArgument("event_manager", eventManager);
				ScriptManager.bindArgument("this_guid", guid);
				ScriptManager.bindArgument("other_guid", cEvent.getOid1());
				ScriptManager.bindArgument("time", eventManager.getTime());
				ScriptManager.loadScript(EConstant.SCRIPT_HOME + damageScript);
				ScriptManager.executeScript();
			}
		}
	}

}

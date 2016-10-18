package component;

import event.Event;
import event.EventManager;

public interface Component {
	
	public void update();
	
	public void onEvent(Event event);
	
}

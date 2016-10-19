package component;

import event.Event;

public interface Component {
	
	public void update();
	
	public void onEvent(Event event);
	
}

package gameobjectcomponent;

import event.Event;
import event.EventManager;

public interface Component {
	
	public void update();
	
	public void register(EventManager em);
	
	public void onEvent(Event event);
	
}

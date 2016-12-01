package rendering;

import java.util.LinkedList;
import event.Event;
import event.EventHandler;
import event.EventManager;
import event.RenderComponentEvent;
import util.EConstant;

public class Scene extends LinkedList<Shape> implements EventHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7882389386746450738L;
	private EventManager eventManager;

	public Scene(EventManager eventManager) {
		super();
		this.eventManager = eventManager;
		register();
	}

	@Override
	public void register() {
		eventManager.register(EConstant.RENDER_COMPONENT_EVENT, this);
	}

	@Override
	public synchronized void onEvent(Event event) {
		if (event.getType() == EConstant.RENDER_COMPONENT_EVENT) {
			add(((RenderComponentEvent) event).getShape());
		}
	}
	
	public static int scaleX(double a) {
		return (int) (a / EConstant.WORLD_WIDTH * EConstant.WINDOW_WIDTH);
	}
	
	public static int scaleY(double a) {
		return (int) (a / EConstant.WORLD_HEIGHT * EConstant.WINDOW_HEIGHT);
	}

	@Override
	public int getGuid() {
		// TODO Auto-generated method stub
		return 0;
	}
}

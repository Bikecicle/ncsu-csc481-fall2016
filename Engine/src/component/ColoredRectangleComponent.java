package component;

import event.Event;
import event.EventManager;
import event.RenderComponentEvent;
import rendering.ColoredRect;
import rendering.SceneManager;
import util.EConstant;

public class ColoredRectangleComponent implements Component, Renderable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1741938692736049328L;
	private SceneManager sceneManager;
	private EventManager eventManager;
	private WorldPositionComponent position;
	private double width;
	private double height;

	public ColoredRectangleComponent(SceneManager sceneManager, EventManager eventManager,
			WorldPositionComponent position, double width,
			double height) {
		this.sceneManager = sceneManager;
		this.eventManager = eventManager;
		this.position = position;
		this.width = width;
		this.height = height;

		eventManager.register(EConstant.RENDER_COMPONENT_EVENT, this);
		eventManager.register(EConstant.RENDER_ALL_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.RENDER_ALL_EVENT) {
			eventManager.raise(new RenderComponentEvent(eventManager.getTime(), this));
		} else if (event.getType() == EConstant.RENDER_COMPONENT_EVENT) {
			if (((RenderComponentEvent) event).getComponent() == this)
				render();
		}
	}
	
	@Override
	public void render() {
		sceneManager.render(new ColoredRect(position.getX(), position.getY(), width, height));
	}
}

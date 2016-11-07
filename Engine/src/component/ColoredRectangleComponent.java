package component;

import event.Event;
import event.EventManager;
import event.RenderComponentEvent;
import rendering.ColoredRect;
import rendering.SceneManager;
import util.EConstant;

public class ColoredRectangleComponent implements Component, Renderable {

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
	}

	@Override
	public void update() {
		eventManager.raise(new RenderComponentEvent(eventManager.getTime(), this));
	}

	@Override
	public void onEvent(Event event) {
		if (((RenderComponentEvent) event).getComponent() == this)
			render();
	}
	
	@Override
	public void render() {
		sceneManager.render(new ColoredRect(position.getX(), position.getY(), width, height));
	}
}

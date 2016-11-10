package component;

import event.Event;
import event.EventManager;
import event.RenderComponentEvent;
import rendering.ColoredRect;
import rendering.Scene;
import util.EConstant;

public class ColoredRectangleComponent extends Component implements Renderable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1741938692736049328L;
	private Scene scene;
	private WorldPositionComponent position;
	private double width;
	private double height;

	public ColoredRectangleComponent(int id, EventManager eventManager, Scene scene, WorldPositionComponent position, double width,
			double height) {
		super(id, eventManager);
		this.eventManager = eventManager;
		this.scene = scene;
		this.position = position;
		this.width = width;
		this.height = height;
		register();
	}
	
	@Override
	public void register() {
		eventManager.register(EConstant.RENDER_COMPONENT_EVENT, this);
		eventManager.register(EConstant.RENDER_ALL_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.RENDER_ALL_EVENT) {
			eventManager.raise(new RenderComponentEvent(eventManager.getTime(), this.getOid()));
		} else if (event.getType() == EConstant.RENDER_COMPONENT_EVENT) {
			if (((RenderComponentEvent) event).getOid() == this.getOid())
				render();
		}
	}

	@Override
	public void render() {
		scene.render(new ColoredRect(position.getX(), position.getY(), width, height));
	}
}

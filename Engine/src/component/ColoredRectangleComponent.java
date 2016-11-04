package component;

import event.Event;
import event.EventManager;
import rendering.ColoredRect;
import rendering.SceneManager;
import util.EConstant;

public class ColoredRectangleComponent implements Component {

	private SceneManager sceneManager;
	private WorldPositionComponent position;
	private double width;
	private double height;
	private boolean rendered;

	public ColoredRectangleComponent(SceneManager sceneManager, EventManager eventManager,
			WorldPositionComponent position, double width,
			double height) {
		this.sceneManager = sceneManager;
		this.position = position;
		this.width = width;
		this.height = height;
		this.rendered = true;

		eventManager.register(EConstant.RENDER_EVENT, this);
	}

	@Override
	public void update() {
		if (!rendered) {
			sceneManager.render(new ColoredRect(position.getX(), position.getY(), width, height));
			rendered = true;
		}
	}

	@Override
	public void onEvent(Event event) {
		rendered = false;
	}
}

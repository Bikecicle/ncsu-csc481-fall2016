package component;

import event.Event;
import event.EventManager;
import event.RenderComponentEvent;
import rendering.Rectangle;
import util.EConstant;

public class ColoredRectangleComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1741938692736049328L;
	private WorldPositionComponent position;
	private double width;
	private double height;

	public ColoredRectangleComponent(int guid, EventManager eventManager, WorldPositionComponent position, double width,
			double height) {
		super(guid, eventManager);
		this.eventManager = eventManager;
		this.position = position;
		this.width = width;
		this.height = height;
		register();
	}

	@Override
	public void register() {
		eventManager.register(EConstant.RENDER_ALL_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.RENDER_ALL_EVENT) {
			eventManager.raise(new RenderComponentEvent(eventManager.getTime(),
					new Rectangle(position.getX(), position.getY(), width, height)));
		}
	}
}

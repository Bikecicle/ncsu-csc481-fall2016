package gameobjectcomponent;

import event.Event;
import event.EventManager;

public class RectangleProfileComponent implements Component {
	
	private WorldPositionComponent position;
	

	public RectangleProfileComponent(WorldPositionComponent position, int width, int height) {
		this.position = position;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void register(EventManager em) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
	
	

}

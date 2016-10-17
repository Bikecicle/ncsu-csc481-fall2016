package gameobjectcomponent;

import event.Event;
import event.EventManager;

public class WorldPositionComponent implements Component {
	private int x;
	private int y;

	public WorldPositionComponent(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public WorldPositionComponent() {
		this.x = 0;
		this.y = 0;
	}

	@Override
	public void update() {
		// Do nothing

	}

	@Override
	public void register(EventManager em) {
		// TODO Auto-generated method stub
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

}

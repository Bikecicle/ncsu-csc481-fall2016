package event;

import util.EConstant;

public class KeyReleasedEvent extends Event {

	private int key, id;

	public KeyReleasedEvent(int key, int id) {
		super(EConstant.KEY_RELEASED_EVENT, EConstant.PRIORITY_INPUT);
		this.key = key;
		this.id = id;
	}

	public int getKey() {
		return key;
	}
	
	public int getId() {
		return id;
	}

}

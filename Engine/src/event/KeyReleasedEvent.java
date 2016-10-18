package event;

import util.EConstant;

public class KeyReleasedEvent extends Event {

	private int key;

	public KeyReleasedEvent(int key) {
		super(EConstant.KEY_RELEASED_EVENT, EConstant.PRIORITY_LOW);
		this.key = key;
	}

	public int getKey() {
		return key;
	}

}

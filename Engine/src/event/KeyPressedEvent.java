package event;

import util.EConstant;

public class KeyPressedEvent extends Event {

	private int key;

	public KeyPressedEvent(int key) {
		super(EConstant.KEY_PRESSED_EVENT, EConstant.PRIORITY_LOW);
		this.key = key;
	}

	public int getKey() {
		return key;
	}

}

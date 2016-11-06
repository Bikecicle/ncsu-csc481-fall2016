package event;

import util.EConstant;

public class KeyPressedEvent extends Event {

	private int key, id;

	public KeyPressedEvent(int key, int id) {
		super(EConstant.KEY_PRESSED_EVENT, EConstant.PRIORITY_INPUT);
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

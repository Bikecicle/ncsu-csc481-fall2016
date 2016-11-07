package event;

import util.EConstant;

public class KeyPressedEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2580837065199009993L;
	private int key, id;

	public KeyPressedEvent(long timestamp, int key, int id) {
		super(timestamp, EConstant.KEY_PRESSED_EVENT, EConstant.PRIORITY_INPUT);
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

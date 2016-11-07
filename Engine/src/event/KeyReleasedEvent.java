package event;

import util.EConstant;

public class KeyReleasedEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5834040588387078812L;
	private int key, id;

	public KeyReleasedEvent(long timestamp, int key, int id) {
		super(timestamp, EConstant.KEY_RELEASED_EVENT, EConstant.PRIORITY_INPUT);
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

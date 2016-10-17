package event;

import util.EngineConstants;

public class KeyReleasedEvent extends Event {

	private int key;

	public KeyReleasedEvent(int key) {
		super("KeyReleased", EngineConstants.PRIORITY_LOW);
		this.key = key;
	}

	public int getKey() {
		return key;
	}

}

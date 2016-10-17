package event;

import util.EngineConstants;

public class KeyPressedEvent extends Event {

	private int key;

	public KeyPressedEvent(int key) {
		super("KeyPressed", EngineConstants.PRIORITY_LOW);
		this.key = key;
	}

	public int getKey() {
		return key;
	}

}

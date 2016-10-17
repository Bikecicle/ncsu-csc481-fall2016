package util;

import java.io.Serializable;

public class KeyInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7581540349898753415L;

	private int key;
	private boolean pressed;

	public KeyInput(int key, boolean pressed) {
		this.key = key;
		this.pressed = pressed;
	}

	public int getKey() {
		return key;
	}

	public boolean getPressed() {
		return pressed;
	}

}

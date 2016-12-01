package util;

import java.util.HashMap;

public class PressedKeyMap {

	private HashMap<Integer, Boolean> map;

	public PressedKeyMap() {
		this.map = new HashMap<Integer, Boolean>();
	}

	// Returns was the key already pressed?
	public boolean press(int key) {
		if (!map.containsKey(key)) {
			map.put(key, true);
			return false;
		}
		if (!map.get(key)) {
			map.replace(key, true);
			return false;
		}
		return true;
	}
	
	public void release(int key){
		if (!map.containsKey(key)) {
			map.put(key, false);
		}
		map.replace(key, false);
	}
	
	public boolean pressed(int key){
		return map.get(key);
	}
}

package event;

import java.io.Serializable;

public class Event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5238937547210725759L;
	private int type;
	private int priority;
	private long timestamp;
	
	public Event(long timestamp, int type, int priority) {
		this.timestamp = timestamp;
		this.type = type;
		this.priority = priority;
	}
	
	public int getType() {
		return type;
	}

	public int getPriority() {
		return priority;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void promote() {
		if (priority > 0)
			priority--;
	}
}

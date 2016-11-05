package event;

public class Event {
	
	int type;
	int priority;
	long timestamp;
	
	public Event(int type, int priority) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	public int getPriority() {
		return priority;
	}
	
	public void promote() {
		if (priority > 0)
			priority--;
	}
	
	public String toString() {
		return Integer.toString(type);
	}
	
}

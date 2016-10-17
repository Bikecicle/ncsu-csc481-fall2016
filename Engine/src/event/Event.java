package event;

public class Event {
	
	int type;
	int priority;
	
	public Event(String type, int priority) {
		this.type = type.hashCode();
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
	
}

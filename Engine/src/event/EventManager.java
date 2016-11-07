package event;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import time.Timeline;

public class EventManager {

	private HashMap<Integer, List<EventHandler>> registeredMap;
	private PriorityQueue<Event> eventQueue;
	private Timeline gameTime;

	public EventManager(Timeline gameTime) {
		this.registeredMap = new HashMap<Integer, List<EventHandler>>();
		this.eventQueue = new PriorityQueue<Event>(new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				int c = o1.getPriority() - o2.getPriority();
				if (c == 0)
					return (int) Math.signum(o2.getTimestamp() - o1.getTimestamp());
				return c;
			}
		});
		this.gameTime = gameTime;
	}

	public synchronized void register(int eventType, EventHandler handler) {
		List<EventHandler> handlers = registeredMap.get(eventType);
		if (handlers == null) {
			handlers = new LinkedList<EventHandler>();
			registeredMap.put(eventType, handlers);
		}
		handlers.add(handler);
		//System.out.println("Component registered as handler for event type: " + eventType );
	}

	public synchronized void raise(Event event) {
		eventQueue.add(event);
		//System.out.println("Event Raised...");
		//System.out.println(eventQueue.toString());
	}

	public synchronized void handle() {
		if (hasEvents()) {
			Event event = eventQueue.poll();

			List<EventHandler> handlers = registeredMap.get(event.getType());
			if (handlers != null) {
				for (EventHandler handler : handlers) {
					handler.onEvent(event);
				}
			}
			//System.out.println("Event Handled...");
			//System.out.println(eventQueue.toString());
		}
	}
	
	public void handleAll() {
		while (hasEvents()) {
			handle();
		}
	}

	public boolean hasEvents() {
		return !eventQueue.isEmpty();
	}
	
	public String toString() {
		return registeredMap.toString();
	}

	public long getTime() {
		return gameTime.getTime();
	}
}

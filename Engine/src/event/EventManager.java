package event;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import time.Timeline;

public class EventManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7696820900006657202L;
	private HashMap<Integer, List<EventHandler>> registeredMap;
	private PriorityQueue<Event> eventQueue;
	private Timeline gameTime;

	public EventManager(Timeline gameTime) {
		this.registeredMap = new HashMap<Integer, List<EventHandler>>();
		this.eventQueue = new PriorityQueue<Event>(new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				int c = (int) Math.signum(o2.getTimestamp() - o1.getTimestamp());
				if (c == 0)
					return o1.getPriority() - o2.getPriority();
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

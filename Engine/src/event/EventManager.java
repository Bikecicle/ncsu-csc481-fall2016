package event;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import gameobjectcomponent.Component;

public class EventManager {

	private HashMap<Integer, List<Component>> registeredMap;
	private PriorityQueue<Event> eventQueue;

	public EventManager() {
		this.registeredMap = new HashMap<Integer, List<Component>>();
		this.eventQueue = new PriorityQueue<Event>(new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return o1.getPriority() - o2.getPriority();
			}
		});
	}

	public void register(int eventType, Component handler) {
		List<Component> handlers = registeredMap.get(eventType);
		if (handlers == null) {
			handlers = new LinkedList<Component>();
			registeredMap.put(eventType, handlers);
		}
		handlers.add(handler);
	}

	public synchronized void raise(Event event) {
		eventQueue.add(event);
	}

	public synchronized void handle() {
		if (hasEvents()) {
			Event event = eventQueue.poll();
			List<Component> handlers = registeredMap.get(event.getType());
			if (handlers != null) {
				for (Component handler : handlers) {
					handler.onEvent(event);

				}
			}
		}
	}

	public boolean hasEvents() {
		return eventQueue.isEmpty();
	}
}

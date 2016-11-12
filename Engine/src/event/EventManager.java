package event;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import time.Timeline;
import util.EConstant;

public class EventManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7696820900006657202L;
	private HashMap<Integer, List<EventHandler>> registrar;
	private PriorityQueue<Event> eventQueue;
	private WorkerThread[] workers;
	private Timeline gameTime;

	public EventManager(Timeline gameTime) {
		this.registrar = new HashMap<Integer, List<EventHandler>>();
		this.eventQueue = new PriorityQueue<Event>(new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				int c = (int) Math.signum(o2.getTimestamp() - o1.getTimestamp());
				if (c == 0)
					return o1.getPriority() - o2.getPriority();
				return c;
			}
		});
		this.workers = new WorkerThread[EConstant.THREAD_POOL_SIZE];
		for (int i = 0; i < EConstant.THREAD_POOL_SIZE; i++) {
			workers[i] = new WorkerThread(i + 1);
			workers[i].start();
		}
		this.gameTime = gameTime;
	}

	public void register(int eventType, EventHandler handler) {
		List<EventHandler> handlers = registrar.get(eventType);
		if (handlers == null) {
			handlers = new LinkedList<EventHandler>();
			registrar.put(eventType, handlers);
		}
		handlers.add(handler);
	}
	
	public void registerWildcard(EventHandler handler) {
		for (int i = 2; i < EConstant.TOTAL_EVENT_TYPES; i++) {
			register(i, handler);
		}
	}

	public void raise(Event event) {
		synchronized (eventQueue) {
			eventQueue.add(event);
			eventQueue.notifyAll();
		}
	}
	
	public void clear() {
		eventQueue.clear();
	}

	public List<EventHandler> getHandlers(Event event) {
		return registrar.get(event.getType());
	}

	public String toString() {
		return registrar.toString();
	}

	public long getTime() {
		return gameTime.getTime();
	}
	
	public Timeline getGameTime() {
		return gameTime;
	}

	private class WorkerThread extends Thread {

		private int id;

		public WorkerThread(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			System.out.println("Worker Thread " + id + " started");
			while (true) {
				try {
					Event event = null;
					synchronized (eventQueue) {
						while (eventQueue.isEmpty())
							eventQueue.wait();
						event = eventQueue.poll();
					}
					// System.out.println("[Worker" + id + "]: " + event);
					List<EventHandler> handlers = registrar.get(event.getType());
					if (handlers != null) {
						for (EventHandler handler : handlers) {
							handler.onEvent(event);
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

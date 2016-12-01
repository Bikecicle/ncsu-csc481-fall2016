package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import event.Event;
import event.EventHandler;
import event.EventManager;
import util.EConstant;

public class ClientOutThread implements Runnable, EventHandler {

	private ObjectOutputStream stream;
	private ConcurrentLinkedQueue<Event> outgoingEvents;
	private EventManager eventManager;
	private boolean stopped;

	public ClientOutThread(ObjectOutputStream stream, EventManager eventManager) {
		this.stream = stream;
		this.outgoingEvents = new ConcurrentLinkedQueue<Event>();
		this.eventManager = eventManager;
		this.stopped = false;
		register();
	}

	@Override
	public void run() {
		try {
			while (!stopped) {
				Event event;
				synchronized (outgoingEvents) {
					while (outgoingEvents.isEmpty())
						outgoingEvents.wait();
					event = outgoingEvents.poll();
				}
				stream.writeObject(event);
			}
			stream.close();
		} catch (IOException | InterruptedException e) {
			// Do nothing for now
		}
	}

	public void stop() {
		stopped = true;
	}

	@Override
	public synchronized void onEvent(Event event) {
		synchronized (outgoingEvents) {
			outgoingEvents.add(event);
			outgoingEvents.notify();
		}
	}

	@Override
	public void register() {
		eventManager.register(EConstant.KEY_PRESSED_EVENT, this);
		eventManager.register(EConstant.KEY_RELEASED_EVENT, this);
		eventManager.register(EConstant.WORLD_REQUEST_EVENT, this);
	}

}

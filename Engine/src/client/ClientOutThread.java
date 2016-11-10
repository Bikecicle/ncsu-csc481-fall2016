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
				if (!outgoingEvents.isEmpty()) {
					stream.writeObject(outgoingEvents.poll());
				}
			}
			stream.close();
		} catch (IOException e) {
			// Do nothing for now
		}
	}

	public void stop() {
		stopped = true;
	}

	@Override
	public void onEvent(Event event) {
		outgoingEvents.add(event);
	}

	@Override
	public void register() {
		eventManager.register(EConstant.KEY_PRESSED_EVENT, this);
		eventManager.register(EConstant.KEY_RELEASED_EVENT, this);
	}

}

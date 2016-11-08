package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import event.Event;
import event.EventHandler;
import event.EventManager;
import util.EConstant;

public class ServerOutThread implements Runnable, EventHandler {

	private ObjectOutputStream stream;
	private ConcurrentLinkedQueue<Event> outgoingEvents;
	private int id;
	private boolean stopped;

	public ServerOutThread(ObjectOutputStream stream, EventManager eventManager, int id) {
		this.stream = stream;
		this.outgoingEvents = new ConcurrentLinkedQueue<Event>();
		this.id = id;
		this.stopped = false;

		eventManager.register(EConstant.OBJECT_MOVED_EVENT, this);
	}

	@Override
	public void run() {
		try {
			stream.writeInt(id);
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

}

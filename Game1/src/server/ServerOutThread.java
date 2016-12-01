package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Queue;
import event.Event;
import event.EventHandler;
import event.EventManager;
import util.EConstant;

public class ServerOutThread implements Runnable, EventHandler {

	private ObjectOutputStream stream;
	private Queue<Event> outgoingEvents;
	private EventManager eventManager;
	private int id;
	private boolean stopped;

	public ServerOutThread(ObjectOutputStream stream, EventManager eventManager, int id) {
		this.stream = stream;
		this.outgoingEvents = new LinkedList<Event>();
		this.eventManager = eventManager;
		this.id = id;
		this.stopped = false;
		register();
	}

	@Override
	public void run() {
		try {
			stream.writeInt(id);
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
	public void onEvent(Event event) {
		synchronized (outgoingEvents) {
			outgoingEvents.add(event);
			outgoingEvents.notify();
		}
	}

	@Override
	public void register() {
		eventManager.register(EConstant.OBJECT_MOVED_EVENT, this);
		eventManager.register(EConstant.SEND_WORLD_EVENT, this);
	}

}

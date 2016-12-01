package server;

import java.io.IOException;
import java.io.ObjectInputStream;

import event.ClientDisconnectEvent;
import event.Event;
import event.EventManager;

public class ServerInThread implements Runnable {

	private ObjectInputStream stream;
	private EventManager eventManager;
	private boolean stopped;
	int id;

	public ServerInThread(ObjectInputStream stream, EventManager eventManager, int id) {
		this.stream = stream;
		this.eventManager = eventManager;
		this.stopped = false;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			while (!stopped) {
				Event event = (Event) stream.readObject();
				eventManager.raise(event);
			}
			stream.close();
		} catch (ClassNotFoundException | IOException e) {
			eventManager.raise(new ClientDisconnectEvent(eventManager.getTime(), id));
		}
	}

	public void stop() {
		stopped = true;
	}

}

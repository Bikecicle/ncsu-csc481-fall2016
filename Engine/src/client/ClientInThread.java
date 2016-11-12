package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import event.ConnectionLostEvent;
import event.Event;
import event.EventManager;

public class ClientInThread implements Runnable {

	private ObjectInputStream stream;
	private EventManager eventManager;
	private boolean stopped;
	private boolean paused;

	public ClientInThread(ObjectInputStream stream, EventManager eventManager) {
		this.stream = stream;
		this.eventManager = eventManager;
		this.stopped = false;
		this.paused = false;
	}

	@Override
	public void run() {
		try {
			EngineClient.id = stream.readInt();
			System.out.println("Server has designated you as client " + EngineClient.id);
			while (!stopped) {
				Event event = (Event) stream.readObject();
				if (!paused)
					eventManager.raise(event);
			}
		} catch (IOException | ClassNotFoundException e) {
			eventManager.raise(new ConnectionLostEvent(eventManager.getTime()));
		}
	}

	public void stop() {
		stopped = true;
	}

	public void pause() {
		paused = true;
		System.out.println("Incoming communications blocked");
	}

	public void resume() {
		paused = false;
		System.out.println("Communications resumed");
	}
}

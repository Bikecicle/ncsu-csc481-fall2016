package serverold;

import java.io.IOException;
import java.io.ObjectInputStream;

import event.ClientDisconnectEvent;
import event.EventManager;
import event.KeyPressedEvent;
import event.KeyReleasedEvent;
import util.KeyInput;

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
				KeyInput input = (KeyInput) stream.readObject();
				if (input.getPressed()) {
					eventManager.raise(new KeyPressedEvent(eventManager.getTime(), input.getKey(), id));
				} else {
					eventManager.raise(new KeyReleasedEvent(eventManager.getTime(), input.getKey(), id));
				}
				//System.out.println("Key Event: " + input.getKey() + " by client " + id + " - pressed = " + input.getPressed());
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

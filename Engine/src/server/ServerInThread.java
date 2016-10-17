package server;

import java.io.IOException;
import java.io.ObjectInputStream;

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
					eventManager.raise(new KeyPressedEvent(input.getKey()));
				} else {
					eventManager.raise(new KeyReleasedEvent(input.getKey()));
				}
				System.out.println("Key Event: " + input.getKey() + " by client " );
			}
			stream.close();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		stopped = true;
	}

}

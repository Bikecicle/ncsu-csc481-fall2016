package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import rendering.Scene;

public class ClientInThread implements Runnable {

	private ObjectInputStream stream;
	private boolean stopped;

	public ClientInThread(ObjectInputStream stream) {
		this.stream = stream;
		this.stopped = false;
	}

	@Override
	public void run() {
		while (!stopped) {
			try {
				EngineClient.scene = (Scene) stream.readObject();
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Connection to server lost");
				stop();
			}
		}
	}

	public void stop() {
		stopped = true;
	}

}

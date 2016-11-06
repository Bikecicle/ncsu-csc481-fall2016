package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import rendering.SceneManager;

public class ServerOutThread implements Runnable {

	private ObjectOutputStream stream;
	private boolean stopped;
	private SceneManager sceneManager;

	public ServerOutThread(ObjectOutputStream stream, SceneManager sceneManager) {
		this.stream = stream;
		this.stopped = false;
		this.sceneManager = sceneManager;
	}

	@Override
	public void run() {
		while (!stopped) {
			if (!sceneManager.isEmpty()) {
				try {
					stream.writeObject(sceneManager.read());
				} catch (IOException e) {
					stop();
				}
			}
		}
	}

	public void stop() {
		stopped = true;
	}

}

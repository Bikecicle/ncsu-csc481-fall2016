package server;

import java.io.ObjectOutputStream;

public class ServerOutThread implements Runnable {

	private ObjectOutputStream stream;
	private boolean stopped;

	public ServerOutThread(ObjectOutputStream stream) {
		this.stream = stream;
		this.stopped = false;
	}

	@Override
	public void run() {
		while (!stopped) {

		}
	}

	public void stop() {
		// TODO Auto-generated method stub

	}

}

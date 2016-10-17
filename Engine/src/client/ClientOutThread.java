package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import util.KeyInput;

public class ClientOutThread implements Runnable {

	private ObjectOutputStream out;
	private boolean stopped;
	private ConcurrentLinkedQueue<KeyInput> queue;

	public ClientOutThread(ObjectOutputStream out) {
		this.out = out;
		this.stopped = false;
		this.queue = new ConcurrentLinkedQueue<KeyInput>();
	}

	@Override
	public void run() {
		try {
			while (!stopped) {
				if (!queue.isEmpty()) {
					out.writeObject(queue.poll());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		stopped = true;
	}

	public void offer(KeyInput keyInput) {
		queue.add(keyInput);
	}

}

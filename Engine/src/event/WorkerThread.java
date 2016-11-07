package event;

public class WorkerThread implements Runnable {

	EventManager eventManager;
	boolean stopped;

	public WorkerThread(EventManager eventManager) {
		this.eventManager = eventManager;
		this.stopped = false;
	}

	@Override
	public void run() {
		while(!stopped) {
		
		}
	}

	public void stop() {
		stopped = true;
	}

}

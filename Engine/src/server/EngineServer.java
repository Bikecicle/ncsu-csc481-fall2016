package server;

import java.util.concurrent.ConcurrentLinkedQueue;

import event.EventManager;
import gameobject.World;

public class EngineServer {

	private ServerSocketListener newConnectionListener;
	private EventManager eventManager;
	private World world;

	private ConcurrentLinkedQueue<ConnectedClient> clients;

	public EngineServer() {
		this.clients = new ConcurrentLinkedQueue<ConnectedClient>();
		this.eventManager = new EventManager();
		this.world = new World();

		this.newConnectionListener = new ServerSocketListener(clients, eventManager);
		new Thread(newConnectionListener).start();
	}

	public World getWorld() {
		return world;
	}

	public boolean noClientsConnected() {
		return clients.isEmpty();
	}

	public void stop() {
		newConnectionListener.stop();
		for (ConnectedClient client : clients) {
			client.stop();
		}
	}

	public static void main(String args[]) {
		EngineServer server = new EngineServer();
		while(true){
			// wat
		}
	}
}

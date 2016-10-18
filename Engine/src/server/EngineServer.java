package server;

import java.util.concurrent.ConcurrentLinkedQueue;

import event.EventManager;
import event.RenderEvent;
import gameobject.Platform;
import gameobject.Player;
import gameobject.World;
import rendering.SceneManager;
import util.EConstant;

public class EngineServer {

	private ServerSocketListener newConnectionListener;
	private EventManager eventManager;
	private SceneManager sceneManager;
	private World world;
	private boolean stopped;
	private ConcurrentLinkedQueue<ConnectedClient> clients;

	public EngineServer() {
		this.clients = new ConcurrentLinkedQueue<ConnectedClient>();
		this.eventManager = new EventManager();
		this.world = new World();
		this.sceneManager = new SceneManager();
		this.stopped = false;

		this.newConnectionListener = new ServerSocketListener(clients, eventManager, sceneManager);
		new Thread(newConnectionListener).start();
	}

	public World getWorld() {
		return world;
	}
	
	public boolean getStopped() {
		return stopped;
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
		EngineServer engineServer = new EngineServer();
		engineServer.initializeWorld();
		System.out.println("Game World Initialized...");
		while (!engineServer.getStopped()) {
			engineServer.gameIteration();
		}
		engineServer.stop();
	}

	private void initializeWorld() {
		world.buildGameObject(new Player(sceneManager, eventManager, 1));
		world.buildGameObject(new Platform(sceneManager, eventManager, 50, 10, 50, 10));
		//world.buildGameObject(new SpawnPoint(world, eventManager, 50, 50));
		System.out.println("Registered handlers: " + eventManager.toString());
	}

	private void gameIteration() {
		long time = System.nanoTime();
		long end = time + EConstant.GAME_LOOP_DT;
		sceneManager.pushCurrent();
		eventManager.raise(new RenderEvent());
		while (time < end) {
			world.update();
			eventManager.handleAll();
			time = System.nanoTime();
		}
		sceneManager.toss();
	}
}

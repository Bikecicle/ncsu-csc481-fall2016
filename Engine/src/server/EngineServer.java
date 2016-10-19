package server;

import java.util.concurrent.ConcurrentLinkedQueue;

import event.EventManager;
import event.RenderEvent;
import gameobject.DeathZone;
import gameobject.MovingPlatform;
import gameobject.Platform;
import gameobject.Player;
import gameobject.SpawnPoint;
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

		this.newConnectionListener = new ServerSocketListener(clients, eventManager, sceneManager, world);
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
		world.buildGameObject(new Platform(sceneManager, eventManager, 50, 10, 50, 10));
		world.buildGameObject(new Platform(sceneManager, eventManager, 20, 80, 20, 20));
		world.buildGameObject(new Platform(sceneManager, eventManager, 30, 30, 30, 5));
		world.buildGameObject(new Platform(sceneManager, eventManager, 80, 40, 10, 30));
		world.buildGameObject(new Platform(sceneManager, eventManager, 30, 20, 10, 20));
		world.buildGameObject(new MovingPlatform(sceneManager, eventManager, 50, 50, 30, 10));

		world.buildGameObject(new DeathZone(eventManager, -5, 50, 10, 100));
		world.buildGameObject(new DeathZone(eventManager, 105, 50, 10, 100));
		world.buildGameObject(new DeathZone(eventManager, 50, -5, 100, 10));
		
		world.buildGameObject(new SpawnPoint(eventManager, 30, 50));
		
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

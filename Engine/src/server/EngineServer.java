package server;

import java.util.concurrent.ConcurrentLinkedQueue;

import event.EventManager;
import event.RenderComponentEvent;
import gameobject.DeathZone;
import gameobject.MovingPlatform;
import gameobject.Platform;
import gameobject.SpawnPoint;
import gameobject.World;
import rendering.SceneManager;
import time.RealTimeline;
import time.SoftTimeline;
import time.Timeline;
import util.EConstant;

public class EngineServer {

	private RealTimeline realTime;
	private SoftTimeline gameTime;
	private Timeline loopTime;
	private EventManager eventManager;
	private SceneManager sceneManager;
	private World world;

	private ConcurrentLinkedQueue<ConnectedClient> clients;
	private ServerSocketListener newConnectionListener;
	private boolean stopped;

	public EngineServer() {
		this.realTime = new RealTimeline();
		this.gameTime = new SoftTimeline(realTime, 1, 1);
		this.loopTime = new Timeline(gameTime, 1);
		this.eventManager = new EventManager(gameTime);
		this.sceneManager = new SceneManager();
		this.world = new World();

		this.clients = new ConcurrentLinkedQueue<ConnectedClient>();
		this.newConnectionListener = new ServerSocketListener(clients, eventManager, sceneManager, world);
		new Thread(newConnectionListener).start();
		this.stopped = false;
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
	
	public void pause() {
		gameTime.setScale(0);
	}
	
	public void resume() {
		gameTime.setScale(1);
	}

	public static void main(String args[]) {
		EngineServer engineServer = new EngineServer();
		engineServer.initializeWorld();
		System.out.println("Game World Initialized...");
		while (!engineServer.getStopped()) {
			// Main Game Loop 60 Hz
			engineServer.loopTime.reset();
			long loopIteration = 1;
			while (engineServer.loopTime.getTime() < loopIteration) {
				engineServer.world.update();
			}
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
}

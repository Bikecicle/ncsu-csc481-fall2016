package server;

import java.util.concurrent.ConcurrentLinkedQueue;

import event.EventManager;
import event.ServiceAllEvent;
import gameobject.DeathZone;
import gameobject.MovingPlatform;
import gameobject.Platform;
import gameobject.SpawnPoint;
import gameobject.World;
import time.RealTimeline;
import time.SoftTimeline;
import time.Timeline;
import util.EConstant;

public class EngineServer {

	private RealTimeline realTime;
	private SoftTimeline gameTime;
	private Timeline loopTime;
	private EventManager eventManager;
	private World world;

	//private WorkerThread[] workers;

	private ConcurrentLinkedQueue<ConnectedClient> clients;
	private ServerSocketListener newConnectionListener;

	private boolean stopped;

	public EngineServer() {
		this.realTime = new RealTimeline();
		this.gameTime = new SoftTimeline(realTime, 1, 1);
		this.loopTime = new Timeline(gameTime, EConstant.GAME_LOOP_DELTA);
		this.eventManager = new EventManager(gameTime);
		this.world = new World("server", eventManager);
/**
		this.workers = new WorkerThread[EConstant.THREAD_POOL_SIZE];
		for (int i = 0; i < EConstant.THREAD_POOL_SIZE; i++) {
			workers[i] = new WorkerThread(eventManager, i + 1);
			(new Thread(workers[i])).start();
		}
*/
		this.clients = new ConcurrentLinkedQueue<ConnectedClient>();
		this.newConnectionListener = new ServerSocketListener(clients, eventManager, world);
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
		engineServer.mainGameLoop();
	}

	private void initializeWorld() {
		world.addGameObject(new Platform("p1", 50, 10, 50, 10));
		world.addGameObject(new Platform("p2", 20, 80, 20, 20));
		world.addGameObject(new Platform("p3", 30, 30, 30, 5));
		world.addGameObject(new Platform("p4", 80, 40, 10, 30));
		world.addGameObject(new Platform("p5", 30, 20, 10, 20));
		world.addGameObject(new MovingPlatform("mp1", 50, 50, 30, 10));

		world.addGameObject(new DeathZone("dz1", -5, 50, 10, 100));
		world.addGameObject(new DeathZone("dz2", 105, 50, 10, 100));
		world.addGameObject(new DeathZone("dz3", 50, -5, 100, 10));

		world.addGameObject(new SpawnPoint("sp1", 30, 50));
		System.out.println("Registered handlers: " + eventManager.toString());
	}

	private void mainGameLoop() {
		loopTime.reset();
		long loopIteration = 0;
		while (!stopped) {
			eventManager.raise(new ServiceAllEvent(eventManager.getTime()));
			while (loopTime.getTime() < loopIteration) {
				// do nothing
			}
			loopIteration++;
		}
		stop();
	}
}

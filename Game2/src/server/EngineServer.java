package server;

import java.util.concurrent.ConcurrentLinkedQueue;

import event.EventManager;
import event.ServiceAllEvent;
import gameobject.Invader;
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

	private ConcurrentLinkedQueue<ConnectedClient> clients;
	private ServerSocketListener newConnectionListener;

	private boolean stopped;

	public EngineServer() {
		this.realTime = new RealTimeline();
		this.gameTime = new SoftTimeline(realTime, realTime.getTime(), 1, 1);
		this.loopTime = new Timeline(gameTime, gameTime.getTime(), EConstant.GAME_LOOP_DELTA);
		this.eventManager = new EventManager(gameTime);
		this.world = new World("server", eventManager);
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
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 11; j++) {
				String str = "in" + (11 * i + j);
				double x = 4.0 * j + 3.0;
				double y = 100 - (4.0 * i) - 3.0;
				double d = 2;
				world.addGameObject(new Invader(str, x, y, d, d));
			}
		}
		world.addGameObject(new Platform("p1", 50, 5, 100, 10));
		world.addGameObject(new Platform("p2", 50, 105, 100, 10));
		world.addGameObject(new SpawnPoint("sp1", 50, 12));
		
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

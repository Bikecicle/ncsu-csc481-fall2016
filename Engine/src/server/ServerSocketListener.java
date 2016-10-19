package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import event.EventManager;
import gameobject.Player;
import gameobject.World;
import rendering.SceneManager;
import util.EConstant;

public class ServerSocketListener implements Runnable {

	private ConcurrentLinkedQueue<ConnectedClient> clients;
	private ServerSocket serverSocket = null;
	private EventManager eventManager;
	private SceneManager sceneManager;
	private World world;
	private int count;
	private boolean stopped;

	public ServerSocketListener(ConcurrentLinkedQueue<ConnectedClient> clients, EventManager eventManager, SceneManager sceneManager, World world) {
		this.clients = clients;
		this.eventManager = eventManager;
		this.sceneManager = sceneManager;
		this.world = world;
		this.stopped = false;
		this.count = 0;
		System.out.println("ServerSocket established on localhost:" + EConstant.PORT);

	}

	public void run() {

		try {
			serverSocket = new ServerSocket(EConstant.PORT);
			while (!stopped) {
				System.out.println("Waiting for clients to connect...");
				Socket socket = serverSocket.accept();
				count++;
				ConnectedClient client = new ConnectedClient(socket, count, eventManager, sceneManager);
				new Thread(client.getIn()).start();
				new Thread(client.getOut()).start();
				client.setStopped(false);
				clients.add(client);
				System.out.println("New connection established: client " + count);
				world.buildGameObject(new Player(sceneManager, eventManager, count));
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		stopped = true;
	}

}

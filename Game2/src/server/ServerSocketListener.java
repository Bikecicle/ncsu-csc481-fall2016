package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import event.ClientDisconnectEvent;
import event.Event;
import event.EventHandler;
import event.EventManager;
import event.WorldRequestEvent;
import gameobject.World;
import gameobject.Character;
import util.EConstant;

public class ServerSocketListener implements Runnable, EventHandler {

	private ConcurrentLinkedQueue<ConnectedClient> clients;
	private ServerSocket serverSocket = null;
	private EventManager eventManager;
	private World world;
	private int count;
	private boolean stopped;

	public ServerSocketListener(ConcurrentLinkedQueue<ConnectedClient> clients, EventManager eventManager,
			World world) {
		this.clients = clients;
		this.eventManager = eventManager;
		this.world = world;
		this.stopped = false;
		this.count = 0;
		register();
		System.out.println("ServerSocket established on localhost:" + EConstant.PORT);
	}

	public void run() {

		try {
			serverSocket = new ServerSocket(EConstant.PORT);
			while (!stopped) {
				System.out.println("Waiting for clients to connect...");
				Socket socket = serverSocket.accept();
				count++;
				ConnectedClient client = new ConnectedClient(socket, count, eventManager);
				new Thread(client.getIn()).start();
				new Thread(client.getOut()).start();
				client.setStopped(false);
				clients.add(client);
				System.out.println("New connection established: client " + count);
				System.out.println("Clients: " + clients.toString());
				world.addGameObject(new Character(("c" + count), count));
				eventManager.raise(new WorldRequestEvent(eventManager.getTime(), "server".hashCode(), EConstant.ALL_GUIDS));
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		stopped = true;
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.CLIENT_DISCONNECT_EVENT) {
			Iterator<ConnectedClient> it = clients.iterator();
			ConnectedClient client = it.next();
			;
			while (it.hasNext() && client.getId() != ((ClientDisconnectEvent) event).getId()) {
				client = it.next();
			}
			client.stop();
			clients.remove(client);
			System.out.println("Client " + ((ClientDisconnectEvent) event).getId() + " disconnected");
			System.out.println("Clients: " + clients.toString());
		}
	}

	@Override
	public void register() {
		eventManager.register(EConstant.CLIENT_DISCONNECT_EVENT, this);
	}

	@Override
	public int getGuid() {
		// TODO Auto-generated method stub
		return 0;
	}
}

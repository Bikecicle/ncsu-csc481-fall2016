package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerSocketListener implements Runnable {

	private boolean isRunning;
	private ConcurrentLinkedQueue<Socket> connections;
	private int count;
	private ServerSocket serverSocket = null;
	private int port;

	public ServerSocketListener(int port, ConcurrentLinkedQueue<Socket> clientSockets) {
		this.port = port;
		this.connections = clientSockets;
		this.isRunning = true;
		this.count = 0;
	}

	public void run() {

		try {
			// Open serverSocket
			serverSocket = new ServerSocket(port);
			System.out.println("[ICH] ServerSocket established on localhost:" + port);
			while (isRunning) {
				System.out.println("[ICH] Waiting for clients to connect...");
				Socket socket = null;
				socket = serverSocket.accept();
				connections.add(socket);
				count++;
				OutputStream out = socket.getOutputStream();
				out.write(count);
				System.out.println("[ICH] New connection established: client " + count);
			}

			// Close serverSocket
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		isRunning = false;
	}

}

package part4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IncomingConnectionHandler implements Runnable {

	private boolean isRunning;
	private ConcurrentLinkedQueue<Socket> connections;
	private int count;
	private ServerSocket serverSocket = null;
	private int port;

	public IncomingConnectionHandler(int port, ConcurrentLinkedQueue<Socket> clientSockets) {
		this.port = port;
		this.connections = clientSockets;
		this.isRunning = true;
		this.count = 0;
	}

	public void run() {
		// Open serverSocket
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[ICH] ServerSocket established on localhost:" + port);
		while (isRunning) {
			System.out.println("[ICH] Waiting for clients to connect...");
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			synchronized (this) {
				connections.add(socket);
				count++;
				try {
					OutputStream out = socket.getOutputStream();
					out.write(count);
					System.out.println("[ICH] New connection established: client " + count);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		// Close serverSocket
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		isRunning = false;
	}

}

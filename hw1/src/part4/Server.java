package part4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server {

	public static int port = 6666;

	public static void main(String args[]) {
		ConcurrentLinkedQueue<Socket> clientSockets = new ConcurrentLinkedQueue<Socket>();
		IncomingConnectionHandler newConnectionHandler = new IncomingConnectionHandler(port, clientSockets);
		new Thread(newConnectionHandler).start();
		System.out.println("ServerSocket thread started");
		int sum = 0;
		boolean notifiedEmpty = false;
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (clientSockets.isEmpty()) {
				if (!notifiedEmpty) {
					System.out.println("No clients connected!");
					notifiedEmpty = true;
				} // else do nothing
			} else {
				notifiedEmpty = false;
				for (Socket socket : clientSockets) {
					try {
						InputStream in = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						out.write(sum);
						int id = in.read();
						sum = in.read();
						System.out.println("Client " + id + " added to the sum. It is now " + sum + ".");
					} catch (IOException e) {
						clientSockets.remove(socket);
					}
				}
			}
		}
	}
}

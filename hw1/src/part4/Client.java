package part4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	private Socket socket;
	private int id;
	private InputStream in;
	private OutputStream out;
	public Client() {
	}

	public static void main(String args[]) {
		Client client = new Client();
		client.connect();
		while (!client.socket.isClosed()) {
			try {
				while (client.in.available() < 1) {}
				int sum = client.in.read();
				int x = (int) (Math.random() * 10) + 1;
				int newSum = sum + x;
				System.out.println("Added " + x + " to the sum. Was: " + sum + " Is now: " + newSum);
				client.out.write(client.id);
				client.out.write(newSum);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Connection with server lost. Shutting down...");
	}

	public void connect() {
		try {
			socket = new Socket("localhost", Server.port);
			System.out.println("Successfully connected to localhost:" + Server.port);
			in = socket.getInputStream();
			out = socket.getOutputStream();
			id = in.read();
			System.out.println("Server has designated you as client " + id);
		} catch (IOException e) {
			System.out.println("Could not connect to localhost:" + Server.port);
			e.printStackTrace();
		}
	}
}

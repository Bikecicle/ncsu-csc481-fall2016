package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import util.EngineConstants;
import util.KeyInput;
import util.PressedKeyMap;

public class EngineClient extends PApplet {

	private Socket socket;
	private ClientInThread in;
	private ClientOutThread out;
	private Scene scene;
	private int id;

	private PressedKeyMap pressedKeyMap;

	public EngineClient() {
		try {
			this.socket = new Socket("localhost", EngineConstants.PORT);
			System.out.println("Successfully connected to localhost:" + EngineConstants.PORT);
			this.out = new ClientOutThread(new ObjectOutputStream(socket.getOutputStream()));
			this.in = new ClientInThread(new ObjectInputStream(socket.getInputStream()));
			new Thread(out).start();
			new Thread(in).start();
			System.out.println("Server has designated you as client " + id);
		} catch (IOException e) {
			System.out.println("Could not connect to localhost:" + EngineConstants.PORT);
			e.printStackTrace();
		}
		this.pressedKeyMap = new PressedKeyMap();
	}

	public static void main(String[] args) {
		PApplet.main("client.EngineClient");
	}

	public void settings() {
	}

	public void setup() {

	}

	public void draw() {

	}

	public void keyPressed() {
		if (!pressedKeyMap.press(keyCode))
			out.offer(new KeyInput(keyCode, true));
	}

	public void keyReleased() {
		pressedKeyMap.release(keyCode);
		out.offer(new KeyInput(keyCode, false));
	}
}

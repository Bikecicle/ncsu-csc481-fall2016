package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import rendering.ColoredRect;
import rendering.Renderable;
import rendering.Scene;
import util.EConstant;
import util.KeyInput;
import util.PressedKeyMap;

public class EngineClient extends PApplet {

	private static Socket socket;
	private static ClientInThread in;
	private static ClientOutThread out;
	public static Scene scene;
	private static int id;

	private static PressedKeyMap pressedKeyMap;

	public static void main(String[] args) {
		try {
			socket = new Socket("localhost", EConstant.PORT);
			System.out.println("Successfully connected to localhost:" + EConstant.PORT);
			System.out.println("Server has designated you as client " + id);
			pressedKeyMap = new PressedKeyMap();
			scene = new Scene();
			out = new ClientOutThread(new ObjectOutputStream(socket.getOutputStream()));
			in = new ClientInThread(new ObjectInputStream(socket.getInputStream()));
			new Thread(out).start();
			new Thread(in).start();
			PApplet.main("client.EngineClient");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void settings() {
		size(EConstant.WINDOW_WIDTH, EConstant.WINDOW_HEIGHT);
	}

	public void setup() {
		fill(255);
		rectMode(CENTER);
	}

	public void draw() {
		background(255);
		List<Renderable> list;
		//System.out.println(scene);
		list = new LinkedList<Renderable>(scene.getList());

		for (Renderable shape : list) {
			int x = scaleX(((ColoredRect) shape).getPositionX());
			int y = scaleY(((ColoredRect) shape).getPositionY());
			int width = scaleX(((ColoredRect) shape).getWidth());
			int height = scaleY(((ColoredRect) shape).getHeight());
			//System.out.println(x + " " + y + " " + width + " " + height);
			fill(0);
			rect(x, EConstant.WINDOW_HEIGHT - y, width, height);
		}
	}

	public void keyPressed() {
		if (!pressedKeyMap.press(keyCode))
			out.offer(new KeyInput(keyCode, true));
	}

	public void keyReleased() {
		pressedKeyMap.release(keyCode);
		out.offer(new KeyInput(keyCode, false));
	}

	private int scaleX(double a) {
		return (int) (a / EConstant.WORLD_WIDTH * EConstant.WINDOW_WIDTH);
	}

	private int scaleY(double a) {
		return (int) (a / EConstant.WORLD_HEIGHT * EConstant.WINDOW_HEIGHT);
	}
}

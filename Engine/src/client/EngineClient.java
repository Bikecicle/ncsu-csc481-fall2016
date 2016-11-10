package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import event.Event;
import event.EventHandler;
import event.EventManager;
import gameobject.World;
import processing.core.PApplet;
import rendering.ColoredRect;
import rendering.Renderable;
import rendering.Scene;
import time.RealTimeline;
import time.SoftTimeline;
import util.EConstant;
import util.KeyInput;
import util.PressedKeyMap;

public class EngineClient extends PApplet implements EventHandler{

	private static Socket socket;
	private static ClientInThread in;
	private static ClientOutThread out;
	private static RealTimeline realTime;
	private static SoftTimeline gameTime;
	private static EventManager eventManager;
	private static World world;
	private static Scene scene;
	public static int id;
	
	private static boolean stopped;

	private static PressedKeyMap pressedKeyMap;

	public static void main(String[] args) {
		try {
			realTime = new RealTimeline();
			gameTime = new SoftTimeline(realTime, 1, 1);
			eventManager = new EventManager(gameTime);
			world = new World(eventManager);
			
			socket = new Socket("localhost", EConstant.PORT);
			System.out.println("Successfully connected to localhost:" + EConstant.PORT);
			pressedKeyMap = new PressedKeyMap();
			scene = new Scene();
			out = new ClientOutThread(new ObjectOutputStream(socket.getOutputStream()));
			in = new ClientInThread(new ObjectInputStream(socket.getInputStream()), eventManager);
			new Thread(out).start();
			new Thread(in).start();
			stopped = false;
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

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}
}

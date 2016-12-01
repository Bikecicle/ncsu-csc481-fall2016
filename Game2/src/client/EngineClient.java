package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import event.Event;
import event.EventHandler;
import event.EventManager;
import event.KeyPressedEvent;
import event.KeyReleasedEvent;
import event.RenderAllEvent;
import gameobject.World;
import processing.core.PApplet;
import rendering.Rectangle;
import rendering.Scene;
import rendering.Shape;
import replay.Replay;
import time.RealTimeline;
import time.SoftTimeline;
import time.Timeline;
import util.EConstant;
import util.PressedKeyMap;

public class EngineClient extends PApplet implements EventHandler {

	private static Socket socket;
	public static ClientInThread in;
	public static ClientOutThread out;
	private static RealTimeline realTime;
	private static SoftTimeline gameTime;
	private static Timeline loopTime;
	private static EventManager eventManager;
	private static World world;
	private static Scene scene;
	private static Replay replay;
	public static int id;

	private static long loopIteration;

	private static PressedKeyMap pressedKeyMap;

	public static void main(String[] args) {
		try {
			realTime = new RealTimeline();
			gameTime = new SoftTimeline(realTime, realTime.getTime(), 1, 1);
			loopTime = new Timeline(gameTime, gameTime.getTime(), EConstant.RENDER_LOOP_DELTA);
			eventManager = new EventManager(gameTime);
			world = new World("client" + id, eventManager);
			scene = new Scene(eventManager);
			replay = new Replay("clientReplay" + id, eventManager);

			socket = new Socket("localhost", EConstant.PORT);
			System.out.println("Successfully connected to localhost:" + EConstant.PORT);
			pressedKeyMap = new PressedKeyMap();
			out = new ClientOutThread(new ObjectOutputStream(socket.getOutputStream()), eventManager);
			in = new ClientInThread(new ObjectInputStream(socket.getInputStream()), eventManager);
			new Thread(out).start();
			new Thread(in).start();

			loopIteration = 0;

			PApplet.main("client.EngineClient");
		} catch (IOException e) {
			System.out.println("Cannot connect to server");
		}
	}

	public void settings() {
		size(EConstant.WINDOW_WIDTH, EConstant.WINDOW_HEIGHT);
	}

	public void setup() {
		frameRate(120);
		fill(255);
		rectMode(CENTER);
		loopTime.reset();
	}

	public void draw() {
		background(200);
		eventManager.raise(new RenderAllEvent(eventManager.getTime()));
		while (loopTime.getTime() < loopIteration) {
			// Wait for next tic
		}
		synchronized (scene) {
			if (!scene.isEmpty()) {
				for (Shape shape : scene) {
					if (shape.getType() == EConstant.RECTANGLE) {
						Rectangle r = (Rectangle) shape;
						fill(0);
						rect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
					}
				}

			}
		}
		scene.clear();
		loopIteration++;
	}

	public void keyPressed() {
		if (!pressedKeyMap.press(keyCode))
			eventManager.raise(new KeyPressedEvent(eventManager.getTime(), keyCode, id));
	}

	public void keyReleased() {
		pressedKeyMap.release(keyCode);
		eventManager.raise(new KeyReleasedEvent(eventManager.getTime(), keyCode, id));
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.CONNECTION_LOST_EVENT) {
			in.stop();
			out.stop();
			System.out.println("Connection lost: exiting...");
			this.exit();
		}
	}

	@Override
	public void register() {
		eventManager.register(EConstant.CONNECTION_LOST_EVENT, this);
	}

	public static World getWorld() {
		return world;
	}

	public static Replay getReplay() {
		return replay;
	}

	@Override
	public int getGuid() {
		// TODO Auto-generated method stub
		return 0;
	}
}

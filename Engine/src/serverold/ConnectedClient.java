package serverold;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import event.EventManager;
import rendering.SceneManager;

public class ConnectedClient {

	private Socket socket;
	private ServerInThread in;
	private ServerOutThread out;
	private int id;
	private boolean stopped;

	public ServerInThread getIn() {
		return in;
	}

	public ServerOutThread getOut() {
		return out;
	}

	public int getId() {
		return id;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public ConnectedClient(Socket socket, int id, EventManager eventManager, SceneManager sceneManager) {
		try {
			this.socket = socket;
			this.in = new ServerInThread(new ObjectInputStream(socket.getInputStream()), eventManager, id);
			this.out = new ServerOutThread(new ObjectOutputStream(socket.getOutputStream()), sceneManager);
			this.id = id;
			this.stopped = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			in.stop();
			out.stop();
			socket.close();
			stopped = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return id + ": " + (stopped ? "stopped" : "running");
	}
}

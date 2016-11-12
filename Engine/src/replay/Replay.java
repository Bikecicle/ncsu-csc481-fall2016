package replay;

import java.util.Iterator;
import java.util.LinkedList;
import client.EngineClient;
import event.Event;
import event.EventHandler;
import event.EventManager;
import event.KeyPressedEvent;
import event.SendWorldEvent;
import time.SoftTimeline;
import util.EConstant;

public class Replay implements EventHandler {

	private EventManager eventManager;
	// private List<GameObject> initialState;
	private LinkedList<Event> log;
	private SoftTimeline replayTime;
	private long start;
	private long end;
	private int guid;
	private boolean playing;

	private boolean recording;

	public Replay(String name, EventManager eventManager) {
		this.eventManager = eventManager;
		this.log = new LinkedList<Event>();
		this.guid = name.hashCode();
		register();
	}

	private void view() {
		System.out.println("Playing replay");
		replayTime = new SoftTimeline(eventManager.getGameTime(), eventManager.getTime() - start, 1, 1);
		Iterator<Event> it = log.iterator();
		Event event = it.next();
		EngineClient.in.pause();
		eventManager.clear();
		// eventManager.raise(new SendWorldEvent(eventManager.getTime(), initialState, guid, EConstant.ALL_GUIDS));
		while (replayTime.getTime() < end) {
			while (event.getTimestamp() > replayTime.getTime()) {
				// Wait
			}
			eventManager.raise(event);
			event = it.next();
		}
		// eventManager.raise(new WorldRequestEvent(eventManager.getTime(),
		// "server".hashCode(), EConstant.ALL_GUIDS));
		System.out.println("Replay complete");
		EngineClient.in.resume();
		playing = false;
	}

	@Override
	public void register() {
		eventManager.registerWildcard(this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.KEY_PRESSED_EVENT) {
			int key = ((KeyPressedEvent) event).getKey();
			if (key == EConstant.KEY_MINUS && playing) { // Slow down replay
				System.out.println("Slowing down replay...");
				replayTime.multiplyScale(0.5);
			} else if (key == EConstant.KEY_PLUS && playing) { // Speed up replay
				System.out.println("Speeding up replay...");
				replayTime.multiplyScale(2);
			} else if (key == EConstant.KEY_P && !playing) { // View (play)
				playing = true;
				view();
			} else if (key == EConstant.KEY_OPEN_BRACKET && !playing) { // Record
				start = -1;
				// eventManager.raise(new
				// WorldRequestEvent(eventManager.getTime(),
				// EngineClient.getWorld().getGuid(), guid));
				log.clear();
				recording = true;
				System.out.println("Recording replay");
			} else if (key == EConstant.KEY_CLOSE_BRACKET && !playing) { // Stop recording
				end = eventManager.getTime();
				recording = false;
				System.out.println("Recording complete - End time: " + end);
			}
		} else if (event.getType() == EConstant.SEND_WORLD_EVENT && ((SendWorldEvent) event).getTarget() == guid) {
			// initialState = ((SendWorldEvent) event).getRoster();
			// System.out.println("Initial replay state set");
			// System.out.println(((SendWorldEvent) event).getRoster());
		} else if (recording == true && event.getType() != EConstant.WORLD_REQUEST_EVENT) {
			log.add(event);
			if (start == -1) {
				start = event.getTimestamp();
				System.out.println("Start time: " + start);
			}
		}
	}
}

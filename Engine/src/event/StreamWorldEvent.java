package event;

import java.util.List;

import gameobject.GameObject;
import util.EConstant;

public class StreamWorldEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5900366372536261883L;
	private List<GameObject> roster;
	private int wid;

	public StreamWorldEvent(long timestamp, List<GameObject> roster, int wid) {
		super(timestamp, EConstant.STREAM_WORLD_EVENT, EConstant.PRIORITY_NETWORKING);
		this.roster = roster;
		this.wid = wid;
	}

	public List<GameObject> getRoster() {
		return roster;
	}
	
	public int getWid() {
		return wid;
	}
}

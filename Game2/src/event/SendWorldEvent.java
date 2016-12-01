package event;

import java.util.List;

import gameobject.GameObject;
import util.EConstant;

public class SendWorldEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5900366372536261883L;
	private List<GameObject> roster;
	private int source;
	private int target;

	public SendWorldEvent(long timestamp, List<GameObject> roster, int source, int target) {
		super(timestamp, EConstant.SEND_WORLD_EVENT, EConstant.PRIORITY_NETWORKING);
		this.roster = roster;
		this.source = source;
		this.target = target;
	}

	public List<GameObject> getRoster() {
		return roster;
	}
	
	public int getSource() {
		return source;
	}
	
	public int getTarget() {
		return target;
	}
}

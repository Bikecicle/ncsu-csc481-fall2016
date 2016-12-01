package event;

import gameobject.GameObject;
import util.EConstant;

public class CreateObjectEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8623248943243978741L;
	private GameObject gameObject;

	public CreateObjectEvent(long timestamp, GameObject gameObject) {
		super(timestamp, EConstant.CREATE_OBJECT_EVENT, EConstant.PRIORITY_GAME_LOGIC);
		this.gameObject = gameObject;
	}

	public GameObject getGameObject() {
		return gameObject;
	}
}

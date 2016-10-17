package gameobject;

import java.util.LinkedList;
import java.util.List;

public class World {

	private List<GameObject> worldObjects;
	
	public World(){
		worldObjects = new LinkedList<GameObject>();
	}
}

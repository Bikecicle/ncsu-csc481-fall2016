package game_object_model.object;

import java.awt.image.ComponentSampleModel;

import game_object_model.component.CollisionBox;
import game_object_model.component.WorldPosition;

public class Platform extends GameObject {

	public Platform( int x, int y, int width, int height ){
		super();
		WorldPosition position = new WorldPosition(x, y);
		CollisionBox hitBox = new CollisionBox(position, width, height);
	}
}

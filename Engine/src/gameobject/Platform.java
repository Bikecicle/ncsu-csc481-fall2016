package gameobject;

import gameobjectcomponent.CollisionBoxComponent;
import gameobjectcomponent.RectangleProfileComponent;
import gameobjectcomponent.WorldPositionComponent;

public class Platform extends GameObject {

	public Platform( World world, int x, int y, int width, int height ) {
		super(world);
		WorldPositionComponent position = new WorldPositionComponent(x, y);
		CollisionBoxComponent hitBox = new CollisionBoxComponent(position, width, height);
		RectangleProfileComponent renderShape = new RectangleProfileComponent(position, width, height);
		
		addComponent(position);
		addComponent(hitBox);
		addComponent(renderShape);
	}
}

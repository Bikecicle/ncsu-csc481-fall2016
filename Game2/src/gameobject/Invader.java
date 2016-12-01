package gameobject;

import java.util.LinkedList;
import java.util.List;

import component.CollisionBoxComponent;
import component.ColoredRectangleComponent;
import component.Component;
import component.CustomDamageComponent;
import component.CustomDriverComponent;
import component.MovementComponent;
import component.WorldPositionComponent;
import event.EventManager;

public class Invader extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6798435359623773863L;
	private double x;
	private double y;
	private double width;
	private double height;
	
	public Invader( String name, double x, double y, double width, double height) {
		super(name);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public List<Component> build(EventManager eventManager) {
		WorldPositionComponent position = new WorldPositionComponent(guid, eventManager, x, y);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(guid, eventManager, position, width, height, false,
				false);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(guid, eventManager, position, width,
				height);
		CustomDriverComponent driver = new CustomDriverComponent(guid, eventManager, "invader_motion.js", x, y);
		CustomDamageComponent damage = new CustomDamageComponent(guid, eventManager, "destroy_on_impact.js");
		MovementComponent movement = new MovementComponent(guid, eventManager, hitbox, driver);

		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(hitbox);
		gameObject.add(renderShape);
		gameObject.add(driver);
		gameObject.add(damage);
		gameObject.add(movement);
		return gameObject;
	}

	@Override
	public List<Component> buildDummy(EventManager eventManager) {
		WorldPositionComponent position = new WorldPositionComponent(guid, eventManager, x, y);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(guid, eventManager, position,
				width, height);
		
		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(renderShape);
		return gameObject;
	}

	@Override
	public boolean hasDummy() {
		return true;
	}

}

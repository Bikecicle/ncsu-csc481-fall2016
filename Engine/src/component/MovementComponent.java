package component;

import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import util.EConstant;

public class MovementComponent implements Component {

	private EventManager eventManager;
	private WorldPositionComponent position;
	private CollisionBoxComponent hitbox;
	private Driver[] drivers;
	private double velocityX, velocityY, accelerationX, accelerationY = 0;
	private long previousTimestamp;
	private double dt;

	public MovementComponent(EventManager eventManager, WorldPositionComponent position, CollisionBoxComponent hitbox,
			Driver... drivers) {
		this.eventManager = eventManager;
		this.position = position;
		this.hitbox = hitbox;
		this.drivers = drivers;
		this.previousTimestamp = System.nanoTime();
	}

	@Override
	public void update() {
		// System.out.println(position);
		long timestamp = System.nanoTime();
		dt = (timestamp - previousTimestamp) / 1000000000.0;
		previousTimestamp = timestamp;
		for (Driver driver : drivers) {
			driver.drive(this);
		}
		velocityX += accelerationX * dt;
		velocityY += accelerationY * dt;
		if (Math.abs(velocityX) < EConstant.MINIMUM_VELOCITY)
			velocityX = 0.0;
		if (Math.abs(velocityY) < EConstant.MINIMUM_VELOCITY)
			velocityY = 0.0;
		position.setPositionX(position.getX() + velocityX * dt);
		position.setPositionY(position.getY() + velocityY * dt);
		eventManager.raise(new ObjectMovedEvent(this));
	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub

	}

	public WorldPositionComponent getPosition() {
		return position;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public double getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}

	public double getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}

	public double getDt() {
		return dt;
	}

	public CollisionBoxComponent getHitbox() {
		return hitbox;
	}

}

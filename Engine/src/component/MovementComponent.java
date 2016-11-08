package component;

import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import event.ServiceComponentEvent;
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
		this.previousTimestamp = eventManager.getTime();

		eventManager.register(EConstant.SERVICE_COMPONENT_EVENT, this);
		eventManager.register(EConstant.SERVICE_ALL_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.SERVICE_ALL_EVENT) {
			eventManager.raise(new ServiceComponentEvent(eventManager.getTime(), this));
		} else if (event.getType() == EConstant.SERVICE_COMPONENT_EVENT) {
			if (((ServiceComponentEvent) event).getComponent() == this) {
				// System.out.println(position);
				long timestamp = eventManager.getTime();
				dt = (timestamp - previousTimestamp) / EConstant.NANOSECONDS_IN_SECOND;
				previousTimestamp = timestamp;
				for (Driver driver : drivers) {
					driver.drive(this);
				}
				velocityX += accelerationX * dt;
				velocityY += accelerationY * dt;
				position.setPositionX(position.getX() + velocityX * dt);
				position.setPositionY(position.getY() + velocityY * dt);
				eventManager.raise(new ObjectMovedEvent(eventManager.getTime(), this));
			}
		}
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

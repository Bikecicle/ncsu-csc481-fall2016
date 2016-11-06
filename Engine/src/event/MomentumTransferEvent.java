package event;

import component.CollisionBoxComponent;
import util.EConstant;

public class MomentumTransferEvent extends Event {

	private CollisionBoxComponent hitbox;
	private double velocityX, velocityY;

	public MomentumTransferEvent(CollisionBoxComponent hitbox, double velocityX, double velocityY) {
		super(EConstant.MOMENTUM_TRANSFER_EVENT, EConstant.PRIORITY_PHYSICS);
		this.hitbox = hitbox;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}

	public CollisionBoxComponent getHitbox() {
		return hitbox;
	}
	
	public double getVelocityX() {
		return velocityX;
	}
	
	public double getVelocityY() {
		return velocityY;
	}
}

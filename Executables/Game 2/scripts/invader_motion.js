function update() {
	t = time - start;
	s = 10 + t / 1000000000.0 / 4;
	movement_component.setVelocityY(-1);
	px = movement_component.getPosition().getX();
	py = movement_component.getPosition().getY();
	vx = movement_component.getVelocityX();
	vy = movement_component.getVelocityY();
	if (px <= initial_x) {
		movement_component.setVelocityX(s);
	} else if (px >= 57 + initial_x) {
		movement_component.setVelocityX(-s);
	}
}
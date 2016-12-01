function update() {
	t = time - start;
	px = movement_component.getPosition().getX();
	py = movement_component.getPosition().getY();
	s = (initial_y - py) / 4 + 10;
	vx = movement_component.getVelocityX();
	vy = movement_component.getVelocityY();
	if (px <= initial_x) {
		movement_component.setVelocityX(s);
	} else if (px >= 57 + initial_x) {
		movement_component.setVelocityX(-s);
	}
	if (vx != movement_component.getVelocityX()) {
		movement_component.setTempPositionY(py - 4);
	}
	
}
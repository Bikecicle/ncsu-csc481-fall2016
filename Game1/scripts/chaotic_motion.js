function update() {
	a = 10.0;
	s = 1000000000.0
	x = initial_x + a * Math.cos(0.5 * time / s);
	y = initial_y + a * Math.cos(0.3 * time / s);
	movement_component.setTempPositionX(x);
	movement_component.setTempPositionY(y);
}
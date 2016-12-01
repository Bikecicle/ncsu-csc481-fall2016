function update() {
	x = initial_x;
	y = initial_y + (time - start) / 1000000000.0 * 50;
	movement_component.setTempPositionX(x);
	movement_component.setTempPositionY(y);
}
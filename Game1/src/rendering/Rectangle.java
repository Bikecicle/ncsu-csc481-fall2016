package rendering;

import util.EConstant;

public class Rectangle extends Shape {

	private double positionX, positionY, width, height;

	public Rectangle(double positionX, double positionY, double width, double height) {
		super(EConstant.RECTANGLE);
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return Scene.scaleX(positionX);
	}

	public int getY() {
		return EConstant.WINDOW_HEIGHT - Scene.scaleY(positionY);
	}

	public int getWidth() {
		return Scene.scaleX(width);
	}

	public int getHeight() {
		return Scene.scaleY(height);
	}

}

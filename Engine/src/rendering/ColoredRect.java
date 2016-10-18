package rendering;

public class ColoredRect implements Renderable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6451782493583285438L;
	private double positionX, positionY, width, height;
	
	public ColoredRect(double positionX, double positionY, double width, double height) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	
}

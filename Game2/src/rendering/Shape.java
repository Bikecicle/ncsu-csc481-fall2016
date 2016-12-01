package rendering;

public abstract class Shape {

	private int type;
	
	public Shape(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}

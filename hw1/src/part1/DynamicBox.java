package part1;

public class DynamicBox extends Box {

	public DynamicBox(int posx, int posy, int width, int height, int color) {
		super(posx, posy, width, height, color);
	}

	public boolean isColliding(Box box) {
		return this.posx < box.posx + box.width && box.posx < this.posx + this.width
				&& this.posy < box.posy + box.height && box.posy < this.posy + this.height;
	}

}

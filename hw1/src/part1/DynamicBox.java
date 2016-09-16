package part1;

import java.util.List;

public class DynamicBox extends Box {

	public final int gravity = 1;

	public float velx;
	public float vely;

	public DynamicBox(int posx, int posy, int width, int height, int[] color) {
		super(posx, posy, width, height, color);
		this.velx = 0;
		this.vely = 0;
	}

	public boolean isColliding(List<Box> environment) {
		for (Box box : environment) {
			if ((this.posx < box.posx + box.width && box.posx < this.posx + this.width
					&& this.posy < box.posy + box.height && box.posy < this.posy + this.height) == true )
				return true;
		}
		return false;
		
	}

	public void updateYPosition(List<Box> environment) {
		vely += gravity;
		posy += vely;
		if (isColliding(environment)) {
			posy -= vely;
			vely = 0;
		}
	}

}

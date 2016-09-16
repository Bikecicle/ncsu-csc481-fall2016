package part1;

import java.util.List;

public class DynamicBox extends Box {

	public final float gravity = 1;
	public final float bounce = -0.5f;

	public float velx;
	public float vely;
	public boolean isJumping;

	public DynamicBox(int posx, int posy, int width, int height, int[] color) {
		super(posx, posy, width, height, color);
		this.velx = 0;
		this.vely = 0;
		this.isJumping = false;
	}

	public boolean isColliding(List<Box> environment) {
		// Other objects
		for (Box box : environment) {
			if ((this.posx < box.posx + box.width && box.posx < this.posx + this.width
					&& this.posy < box.posy + box.height && box.posy < this.posy + this.height) == true)
				return true;
		}
		// Boundaries
		if (this.posx < 0 || this.posx + this.width > 700 || this.posy < 0 || this.posy + this.height > 700) {
			return true;
		}

		return false;

	}

	public void updateYPosition(List<Box> environment) {
		vely += gravity;
		posy += vely;
		if (isColliding(environment)) {
			posy -= vely;
			vely *= bounce;
		}
		if (Math.abs(vely) < 1) {
			vely = 0;
		}
	}

	public void updateXPosition(List<Box> environment) {
		posx += velx;
		if (isColliding(environment)) {
			posx -= velx;
			velx *= bounce;
		}
		if (Math.abs(velx) < 1) {
			velx = 0;
		}
	}
}

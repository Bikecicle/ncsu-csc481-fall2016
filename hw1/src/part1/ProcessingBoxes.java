package part1;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class ProcessingBoxes extends PApplet {

	public final static int[] black = { 0, 0, 0 };
	public final static int[] white = { 255, 255, 255 };
	public final static int[] red = { 255, 0, 0 };
	public final static int[] green = { 0, 255, 0 };
	public final static int[] blue = { 0, 0, 255 };
	public final static int[] yellow = { 255, 255, 0 };

	public final int moveSpeed = 10;

	public List<Box> boxes = new ArrayList<Box>();
	public DynamicBox player = new DynamicBox(200, 200, 15, 15, red);

	public static void main(String[] args) {
		PApplet.main("part1.ProcessingBoxes");

	}

	public void settings() {
		size(700, 700);
	}

	public void setup() {
		fill(120, 50, 240);
		boxes.add(new Box(0, 600, 700, 100, black));
		boxes.add(new Box(300, 500, 200, 70, blue));
		boxes.add(new Box(20, 20, 100, 100, yellow));
		boxes.add(new Box(600, 400, 60, 250, green));
	}

	public void draw() {
		background(255);
		player.updateYPosition(boxes);
		player.updateXPosition(boxes);
		for (Box box : boxes) {
			renderBox(box);
		}
		renderBox(player);
	}

	public void keyPressed() {

		if (key == CODED) {
			if (keyCode == UP && !player.isJumping && player.hasLanded) {
				player.isJumping = true;
				player.hasLanded = false;
				player.vely += -20;
			} else if (keyCode == LEFT) {
				player.velx = -moveSpeed;
			} else if (keyCode == RIGHT) {
				player.velx = moveSpeed;
			}

		}

	}

	public void keyReleased() {
		if (key == CODED) {
			if (keyCode == UP && player.isJumping) {
				player.isJumping = false;
			} else if (keyCode == LEFT) {
				player.velx = 0;
			} else if (keyCode == RIGHT) {
				player.velx = 0;
			}

		}
	}

	public void renderBox(Box box) {
		fill(box.color[0], box.color[1], box.color[2]);
		rect(box.posx, box.posy, box.width, box.height);
	}
}

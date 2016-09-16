package part1;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class ProcessingBoxes extends PApplet {

	public final static int[] black = { 0, 0, 0 };
	public final static int[] white = { 255, 255, 255 };
	public final static int[] red = { 255, 0, 0 };

	public List<Box> boxes = new ArrayList<Box>();
	public DynamicBox player = new DynamicBox(100, 100, 15, 15, red);

	public static void main(String[] args) {
		PApplet.main("part1.ProcessingBoxes");

	}

	public void settings() {
		size(700, 700);
	}

	public void setup() {
		fill(120, 50, 240);
		boxes.add(new Box(0, 690, 700, 10, black));
	}

	public void draw() {
		background(255);
		for (Box box : boxes) {
			renderBox(box);
		}
		player.updateYPosition(boxes);
		renderBox(player);
	}

	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == UP) {
				player.vely += -5;
			}
		}
	}

	public void keyReleased() {

	}

	public void renderBox(Box box) {
		fill(box.color[0], box.color[1], box.color[2]);
		rect(box.posx, box.posy, box.width, box.height);
	}
}

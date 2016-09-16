package part1;

import processing.core.PApplet;

public class ProcessingBoxes extends PApplet{

	public static void main(String[] args) {
		PApplet.main("part1.ProcessingBoxes");

	}
	
	public void settings() {
		size(300,300);
	}
	
	public void setup() {
		fill(120,50,240);
	}
	
	public void draw() { 
		
	}
	
	public void renderBox(Box box) {
		fill(box.color);
		rect(box.posx, box.posy, box.width, box.height);
	}
	
	

}

package com.ralitski.aether.input;

import com.ralitski.util.math.geom.d2.Vector2d;

public class InputBundle {
	
	private Input up, down, left, right;
	private float sense_up = 1;
	private float sense_down = 1;
	private float sense_left = 1;
	private float sense_right = 1;
	
	public InputBundle(Input up, Input down, Input left, Input right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	public Input getUp() {
		return up;
	}

	public void setUp(Input up) {
		this.up = up;
	}

	public Input getDown() {
		return down;
	}

	public void setDown(Input down) {
		this.down = down;
	}

	public Input getLeft() {
		return left;
	}

	public void setLeft(Input left) {
		this.left = left;
	}

	public Input getRight() {
		return right;
	}

	public void setRight(Input right) {
		this.right = right;
	}

	public float getSenseUp() {
		return sense_up;
	}

	public void setSenseUp(float sense_up) {
		this.sense_up = sense_up;
	}

	public float getSenseDown() {
		return sense_down;
	}

	public void setSenseDown(float sense_down) {
		this.sense_down = sense_down;
	}

	public float getSenseLeft() {
		return sense_left;
	}

	public void setSenseLeft(float sense_left) {
		this.sense_left = sense_left;
	}

	public float getSenseRight() {
		return sense_right;
	}

	public void setSenseRight(float sense_right) {
		this.sense_right = sense_right;
	}

	public void update() {
		up.update();
		down.update();
		left.update();
		right.update();
	}
	
	public Vector2d get() {
		Vector2d v = new Vector2d();
		v.addX(right.down() * sense_right);
		v.addX(left.down() * -sense_left);
		v.addY(up.down() * sense_up);
		v.addY(down.down() * -sense_down);
		return v;
	}
}

package com.ralitski.aether.input;

import org.lwjgl.input.Mouse;

public class InputMouseMove extends InputAbstract {

	public static final int UP = 0;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 1;
	
	private int direction;
	private State state;
	private float sensitivity;
	
	public InputMouseMove(float sensitivity) {
		this.sensitivity = sensitivity;
		state = new State();
		state.setCallback(this);
	}

	@Override
	public float down() {
		float move = 0;
		if(direction == UP || direction == DOWN) {
			move = Mouse.getDY();
			if(direction == DOWN) move = -move;
		} else {
			move = Mouse.getDX();
			if(direction == LEFT) move = -move;
		}
		return move * sensitivity;
	}

	@Override
	public void update() {
		state.update(down());
	}

	@Override
	public String getTypeName() {
		return "Mouse Move";
	}

	@Override
	public String getSimpleName() {
		return direction == UP ? "+Y" : (direction == DOWN ? "-Y" : (direction == LEFT ? "-X" : "+X"));
	}

}

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
	
	private int ticks;
	private int prevTicks;
	private float prevMove;
	
	public InputMouseMove(int direction, float sensitivity) {
		this.direction = direction;
		this.sensitivity = sensitivity;
		state = new State();
		state.setCallback(this);
	}

	@Override
	public float down() {
		if(prevTicks != ticks) {
			prevTicks = ticks;
			float move = 0;
			if(direction == UP || direction == DOWN) {
				move = Mouse.getDY();
				if(direction == DOWN) move = -move;
			} else {
				move = Mouse.getDX();
				if(direction == LEFT) move = -move;
			}
			prevMove = move;
		}
		return prevMove * sensitivity;
	}

	@Override
	public void update() {
		state.update(down());
		ticks++;
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

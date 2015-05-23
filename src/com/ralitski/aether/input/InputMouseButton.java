package com.ralitski.aether.input;

import org.lwjgl.input.Mouse;

public class InputMouseButton extends InputAbstract {
	
	private int button;
	private State state;
	
	public InputMouseButton() {
		state = new State();
		state.setCallback(this);
	}

	@Override
	public float down() {
		return Mouse.isButtonDown(button) ? 1 : 0;
	}

	@Override
	public void update() {
		state.update(down());
	}

	@Override
	public String getTypeName() {
		return "Mouse";
	}

	@Override
	public String getSimpleName() {
		return Mouse.getButtonName(button);
	}

}

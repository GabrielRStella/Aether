package com.ralitski.aether.input;

import org.lwjgl.input.Keyboard;

public class InputKeyboard extends InputAbstract {
	
	private int key;
	private State state;
	
	public InputKeyboard(int key) {
		this.key = key;
		state = new State();
		state.setCallback(this);
	}

	@Override
	public float down() {
		return Keyboard.isKeyDown(key) ? 1 : 0;
	}

	@Override
	public void update() {
		state.update(down());
	}

	@Override
	public String getTypeName() {
		return "Keyboard";
	}

	@Override
	public String getSimpleName() {
		return Keyboard.getKeyName(key);
	}

}

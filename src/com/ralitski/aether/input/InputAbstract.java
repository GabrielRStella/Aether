package com.ralitski.aether.input;

import java.util.HashSet;
import java.util.Set;

public abstract class InputAbstract implements Input, StateCallback {
	
	protected Set<InputListener> listeners;
	
	public InputAbstract() {
		listeners = new HashSet<>();
	}
	
	public void addListener(InputListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(InputListener listener) {
		listeners.remove(listener);
	}
	
	public void setState(float prevState, float state, long time) {
		event(prevState, state, time);
	}

	protected void event(float prevState, float state, long time) {
		for(InputListener listener : listeners) {
			listener.onInputChange(this, prevState, state, time);
		}
	}

	@Override
	public String getName() {
		return getTypeName() + ": " + getSimpleName();
	}
}

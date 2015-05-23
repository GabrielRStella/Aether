package com.ralitski.aether.input;

public interface StateCallback {
	
	/**
	 * 
	 * @param prevState
	 * @param state
	 * @param time The amount of time spent on the previous state
	 */
	void setState(float prevState, float state, long time);
}

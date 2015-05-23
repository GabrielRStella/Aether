package com.ralitski.aether.input;

public class State {
	
	private long prevTime;
	private float prevState;
	
	private StateCallback callback;
	
	public void update(float state) {
		if(state != prevState) {
			long time = Timer.getInstance().getTime();
			callback.setState(prevState, state, time - prevTime);
			prevTime = time;
			prevState = state;
		}
	}
	
	public void setCallback(StateCallback callback) {
		this.callback = callback;
	}
}
